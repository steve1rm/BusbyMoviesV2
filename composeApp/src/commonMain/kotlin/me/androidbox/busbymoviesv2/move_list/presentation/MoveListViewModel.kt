package me.androidbox.busbymoviesv2.move_list.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import me.androidbox.busbymoviesv2.configuration.domain.usecases.ConfigurationUseCase
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.presentation.utils.getScreenDensity
import me.androidbox.busbymoviesv2.move_list.domain.usecases.MovieListNowPlayingUseCase

class MoveListViewModel(
    private val movieListNowPlayingUseCase: MovieListNowPlayingUseCase,
    private val configurationUseCase: ConfigurationUseCase
) : ViewModel() {

    var movieListState by mutableStateOf(MovieListState())
        private set

    init {
        viewModelScope.launch {
            println("NETWORK Fetching from the movie endpoint")

            movieListState = movieListState.copy(
                isLoading = true
            )

            val configuration = viewModelScope.async {
                configurationUseCase.execute()
            }

            val movieList = viewModelScope.async {
                movieListNowPlayingUseCase.execute()
            }

            val configurationModel = configuration.await()
            val movieListResult = movieList.await()

            when (movieListResult) {
                is CheckResult.Failure -> {
                    Logger.e { "Fetch from the movie endpoint ${movieListResult.exceptionError}" }

                    movieListState = movieListState.copy(
                        isLoading = false
                    )
                }

                is CheckResult.Success -> {
                    Logger.d { "Success movie list ${movieListResult.data}" }

                    val imageSize = configurationModel?.let {
                        getImageSize(it)
                    } ?: "w500"

                    movieListState = movieListState.copy(
                        isLoading = false,
                        movieList = movieListResult.data.toMovieList(imageSize))
                }
            }

        }
    }
}

private fun getImageSize(configurationResult: ConfigurationModel): String {
    val listOfPosterSizes = configurationResult.imagesModel.posterSizes

    val deviceSize = when (getScreenDensity()) {
        "LDPI" -> "w92"
        "MDPI" -> "w154"
        "HDPI" -> "w185"
        "XHDPI" -> "w342"
        "XXHDPI" -> "w500"
        "XXXHDPI" -> "w780"
        else -> "original"
    }

    val size = listOfPosterSizes.firstOrNull { posterSize ->
        posterSize == deviceSize
    } ?: run {
        "w500"
    }
    return size
}
