package me.androidbox.busbymoviesv2.movie_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.movie_details.domain.MovieDetailUseCase

class MovieDetailViewModel(private val movieDetailUseCase: MovieDetailUseCase) : ViewModel() {

    private val _movieDetailState = MutableStateFlow(MovieDetailState())
    val movieDetailState = _movieDetailState.asStateFlow()

    fun movieDetail(movieId: Int) {
        viewModelScope.launch {
            when(val checkResult = movieDetailUseCase.execute(movieId)) {
                is CheckResult.Failure -> {
                    checkResult.exceptionError
                }
                is CheckResult.Success -> {
                    _movieDetailState.update { movieDetailState ->
                        movieDetailState.copy(
                            movieDetail = checkResult.data.toMovieDetail()
                        )
                    }
                }
            }
        }
    }

    fun onMovieDetailAction(movieDetailAction: MovieDetailAction) {
        when(movieDetailAction) {
            MovieDetailAction.OnFavourateClicked -> TODO()
            MovieDetailAction.OnMovieActorClicked -> TODO()
            MovieDetailAction.OnPlayMainTrailer -> TODO()
            MovieDetailAction.OnReviewClicked -> TODO()
            MovieDetailAction.OnSimilarMovieClicked -> TODO()
        }
    }
}