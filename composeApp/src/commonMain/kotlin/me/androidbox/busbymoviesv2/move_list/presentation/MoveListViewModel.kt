package me.androidbox.busbymoviesv2.move_list.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.move_list.domain.usecases.MovieListNowPlayingUseCase

class MoveListViewModel(
    private val movieListNowPlayingUseCase: MovieListNowPlayingUseCase
) : ViewModel() {

    var movieListState by mutableStateOf(MovieListState())
        private set

    init {
        viewModelScope.launch {
            Logger.d {
                "Fetch from the movie endpoint"
            }

            movieListState = movieListState.copy(
                isLoading = true
            )

            when(val movieList = movieListNowPlayingUseCase.execute()) {
                is CheckResult.Failure -> {
                    Logger.e {
                        "Fetch from the movie endpoint ${movieList.exceptionError}"
                    }
                    movieListState = movieListState.copy(
                        isLoading = false
                    )
                }
                is CheckResult.Success -> {
                    Logger.d {
                        "Success movie list ${movieList.data}"
                    }
                    movieListState = movieListState.copy(
                        isLoading = false,
                        movieList = movieList.data.toMovieList()
                    )
                }
            }
        }
    }
}