package me.androidbox.busbymoviesv2.move_list.presentation

sealed interface MovieListAction {
    data class OnMovieClicked(val movieId: String) : MovieListAction
    data class OnMovieListNavigationItemClicked(val movieCategory: MovieCategories) : MovieListAction
}