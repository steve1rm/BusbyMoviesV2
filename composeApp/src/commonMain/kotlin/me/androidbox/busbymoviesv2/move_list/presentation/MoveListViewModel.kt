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
import me.androidbox.busbymoviesv2.core.data.network.Routes
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.presentation.utils.mapImageSize
import me.androidbox.busbymoviesv2.move_list.domain.usecases.MovieListUseCase

class MoveListViewModel(
    private val movieListUseCase: MovieListUseCase,
    private val configurationUseCase: ConfigurationUseCase
) : ViewModel() {

    var movieListState by mutableStateOf(MovieListState())
        private set

    fun onMovieListAction(action: MovieListAction) {
        when(action) {
            is MovieListAction.OnMovieClicked -> {
                /** Navigate to movie details screen */
                Logger.d {
                    "Movie Item clicked ${action.movieId}"
                }
            }
            is MovieListAction.OnMovieListNavigationItemClicked -> {
                /** Send request to get the movie list of different categories */
                Logger.d {
                    "Movie navigation item clicked ${action.movieCategory.name}"
                }

                nowPlaying(movieListUseCase, action.movieCategory.movieRoute)
            }
        }
    }

    private fun nowPlaying(movieListUseCase: MovieListUseCase, movieRoute: String) {
        viewModelScope.launch {
            movieListState = movieListState.copy(
                isLoading = true
            )

            val configuration = viewModelScope.async {
                configurationUseCase.execute()
            }

            val movieList = viewModelScope.async {
                movieListUseCase.execute(movieRoute)
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

  /*  private fun trending() {
        viewModelScope.launch {
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
    }*/

    init {
        nowPlaying(movieListUseCase, Routes.NOW_PLAYING)
    }
}
