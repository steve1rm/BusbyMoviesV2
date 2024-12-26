package me.androidbox.busbymoviesv2.movie_details.presentation

sealed interface MovieDetailAction {
    data object OnReviewClicked : MovieDetailAction
    data object OnSaveFavouriteClicked : MovieDetailAction
    data object OnDeleteFavouriteClicked : MovieDetailAction
    data object OnMovieActorClicked : MovieDetailAction
    data class OnSimilarMovieClicked(val movieId: Int) : MovieDetailAction
    data class OnHomePageClicked(val url: String) : MovieDetailAction
    data object OnTryAgain : MovieDetailAction
}