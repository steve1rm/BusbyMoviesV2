package me.androidbox.busbymoviesv2.favourites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.favourites.presentation.screens.FavouriteMovieState
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.DeleteFavouriteMovieUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.GetFavouriteMoviesUseCase

class FavouriteMovieViewModel(
    private val getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase,
    private val deleteFavouriteMovieUseCase: DeleteFavouriteMovieUseCase
) : ViewModel() {

    private var hasFetchedFavourites = false

    private val _favouriteMovieState = MutableStateFlow<FavouriteMovieState>(FavouriteMovieState())
    val favouriteMovieState = _favouriteMovieState.asStateFlow()
        .onStart {
            /** Only get when the viewmodel is being created
             *  Prevents fetching again when navigating back to this screen for greater than 5 seconds */
            if(!hasFetchedFavourites) {
                getFavouriteMovies()
                hasFetchedFavourites = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = FavouriteMovieState()
        )

    private fun getFavouriteMovies() {
        getFavouriteMoviesUseCase.execute()
            .onEach { listOfFavouriteMovies ->
                _favouriteMovieState.update { favouriteMovies ->
                    favouriteMovies.copy(
                        listOfFavouriteMovies = listOfFavouriteMovies
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onFavouriteAction(action: FavouriteActions) {
        when(action) {
            is FavouriteActions.OnDeleteFromFavourites -> {
                viewModelScope.launch {
                    deleteFavouriteMovieUseCase.execute(action.movieFavouriteModel)
                }
            }
        }
    }
}