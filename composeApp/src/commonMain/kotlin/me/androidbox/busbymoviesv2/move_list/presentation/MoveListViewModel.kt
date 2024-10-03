package me.androidbox.busbymoviesv2.move_list.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.configuration.domain.usecases.ConfigurationUseCase
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.presentation.utils.mapImageSize
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
                        mapImageSize(it)
                    } ?: "original"

                    movieListState = movieListState.copy(
                        isLoading = false,
                        movieList = movieListResult.data.toMovieList(imageSize))
                }
            }
        }
    }
}
