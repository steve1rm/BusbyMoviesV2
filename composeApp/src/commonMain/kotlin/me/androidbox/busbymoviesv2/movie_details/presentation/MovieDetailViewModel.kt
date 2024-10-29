package me.androidbox.busbymoviesv2.movie_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import me.androidbox.busbymoviesv2.configuration.domain.usecases.ConfigurationUseCase
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.presentation.utils.mapImageSize
import me.androidbox.busbymoviesv2.movie_details.domain.MovieDetailUseCase

class MovieDetailViewModel(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val configurationUseCase: ConfigurationUseCase) : ViewModel() {

    private val _movieDetailState = MutableStateFlow(MovieDetailState())
    val movieDetailState = _movieDetailState.asStateFlow()

    private var configurationModel: ConfigurationModel? = null
    private var imageSize: String = "w500"

    init {
        viewModelScope.launch {
            val configuration = viewModelScope.async {
                configurationUseCase.execute()
            }
            configurationModel = configuration.await()
            configurationModel?.let {
                mapImageSize(it)
            } ?: "original"
        }
    }

    fun movieDetail(movieId: Int) {
        viewModelScope.launch {
            _movieDetailState.update { movieDetailState ->
                movieDetailState.copy(
                    isLoading = true
                )
            }
            when(val checkResult = movieDetailUseCase.execute(movieId)) {
                is CheckResult.Failure -> {
                    checkResult.exceptionError

                    _movieDetailState.update { movieDetailState ->
                        movieDetailState.copy(
                            isLoading = true
                        )
                    }
                }
                is CheckResult.Success -> {
                    _movieDetailState.update { movieDetailState ->
                        movieDetailState.copy(
                            movieDetail = checkResult.data.toMovieDetail(imageSize),
                            isLoading = false
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