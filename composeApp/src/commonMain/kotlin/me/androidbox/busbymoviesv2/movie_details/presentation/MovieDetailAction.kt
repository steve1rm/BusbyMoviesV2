package me.androidbox.busbymoviesv2.movie_details.presentation

sealed interface MovieDetailAction {
    data object OnReviewClicked : MovieDetailAction
    data object OnFavouriteClicked : MovieDetailAction
    data object OnMovieActorClicked : MovieDetailAction
    data class OnSimilarMovieClicked(val movieId: Int) : MovieDetailAction
    data class OnHomePageClicked(val url: String) : MovieDetailAction
}