package me.androidbox.busbymoviesv2.movie_details.presentation

/** Events form the viewModel to the Screen VM => Screen */
sealed interface MovieDetailEvent {
    data object OnFavouriteSaved : MovieDetailEvent
    data object OnFavouriteDeleted : MovieDetailEvent
}