package me.androidbox.busbymoviesv2.movie_details.presentation

sealed interface MovieDetailAction {
    data object OnReviewClicked : MovieDetailAction
    data object OnFavourateClicked : MovieDetailAction
    data object OnMovieActorClicked : MovieDetailAction
    data object OnSimilarMovieClicked : MovieDetailAction
    data object OnPlayMainTrailer : MovieDetailAction
}