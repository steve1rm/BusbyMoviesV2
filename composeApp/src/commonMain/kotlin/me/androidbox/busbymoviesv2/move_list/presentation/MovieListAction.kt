package me.androidbox.busbymoviesv2.move_list.presentation

sealed interface MovieListAction {
    data class OnMovieClicked(val movieId: String) : MovieListAction
}