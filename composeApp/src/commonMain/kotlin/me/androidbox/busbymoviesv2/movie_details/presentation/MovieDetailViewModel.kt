package me.androidbox.busbymoviesv2.movie_details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import me.androidbox.busbymoviesv2.configuration.domain.usecases.ConfigurationUseCase
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.onFailure
import me.androidbox.busbymoviesv2.core.domain.utils.onSuccess
import me.androidbox.busbymoviesv2.core.presentation.utils.mapImageSize
import me.androidbox.busbymoviesv2.move_list.presentation.toMovieList
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.MovieCreditsUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.MovieDetailUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.SimilarMoviesUseCase

class MovieDetailViewModel(
    private val movieDetailUseCase: MovieDetailUseCase,
    private val configurationUseCase: ConfigurationUseCase,
    private val movieCreditsUseCase: MovieCreditsUseCase,
    private val similarMoviesUseCase: SimilarMoviesUseCase,
    private val movieId: Int) : ViewModel() {

    private val _movieDetailState = MutableStateFlow(MovieDetailState())
    val movieDetailState = _movieDetailState.asStateFlow()
        .onStart {
            movieDetail(movieId)
            movieCredits(movieId)
            similarMovies(movieId)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MovieDetailState()
        )

    private var configurationModel: ConfigurationModel? = null
    private var imageSize: String = "w500"

    init {
        viewModelScope.launch {
            println("LAUNCH NETWORK STATUS CANCELLATION")
            val configuration = viewModelScope.async {
                configurationUseCase.execute()
            }
            configurationModel = configuration.await()
            configurationModel?.let {
                mapImageSize(it)
            } ?: "original"
        }
    }

    fun movieCredits(movieId: Int) {
        viewModelScope.launch {
            _movieDetailState.update { movieDetailState ->
                movieDetailState.copy(isLoadingCredits = true)
            }

            movieCreditsUseCase.execute(movieId)
                .onSuccess { creditsModel ->
                    _movieDetailState.update { movieDetailState ->
                        movieDetailState.copy(
                            movieCredits = creditsModel.toCredits(),
                            isLoadingCredits = false
                        )
                    }
                }
                .onFailure { error, errorModel ->
                    _movieDetailState.update { movieDetailState ->
                        movieDetailState.copy(isLoadingCredits = false)
                    }
                    println(error)
                    println(errorModel)
                }
        }
    }

    fun movieDetail(movieId: Int) {
        viewModelScope.launch {
            _movieDetailState.update { movieDetailState ->
                movieDetailState.copy(
                    isLoadingDetails = true
                )
            }

            when (val checkResult = movieDetailUseCase.execute(movieId)) {
                is CheckResult.Failure -> {
                    _movieDetailState.update { movieDetailState ->
                        movieDetailState.copy(
                            isLoadingDetails = true
                        )
                    }
                }

                is CheckResult.Success -> {
                    _movieDetailState.update { movieDetailState ->
                        movieDetailState.copy(
                            movieDetail = checkResult.data.toMovieDetail(imageSize),
                            isLoadingDetails = false
                        )
                    }
                }
            }
        }
    }

    fun similarMovies(movieId: Int) {
        viewModelScope.launch {
            _movieDetailState.update { movieDetailState ->
                movieDetailState.copy(isLoadingSimilarMovies = true)
            }

            similarMoviesUseCase.execute(movieId)
                .onSuccess { listOfMovieDetails ->
                    _movieDetailState.update { movieDetailState ->
                        movieDetailState.copy(
                            listOfMovieDetails = listOfMovieDetails.toMovieList("w500"),
                            isLoadingSimilarMovies = false
                        )
                    }
                }
                .onFailure { error, errorModel ->
                    _movieDetailState.update { movieDetailState ->
                        movieDetailState.copy(
                            isLoadingSimilarMovies = false
                        )
                    }
                }
        }
    }

    fun onMovieDetailAction(movieDetailAction: MovieDetailAction) {
        when(movieDetailAction) {
            MovieDetailAction.OnFavouriteClicked -> TODO()
            MovieDetailAction.OnMovieActorClicked -> TODO()
            MovieDetailAction.OnPlayMainTrailer -> TODO()
            MovieDetailAction.OnReviewClicked -> TODO()
            is MovieDetailAction.OnSimilarMovieClicked -> {
                movieDetail(movieDetailAction.movieId)
                movieCredits(movieDetailAction.movieId)
                similarMovies(movieDetailAction.movieId)
            }
        }
    }
}
