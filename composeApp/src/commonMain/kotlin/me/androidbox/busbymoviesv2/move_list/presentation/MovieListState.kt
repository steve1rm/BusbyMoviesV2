package me.androidbox.busbymoviesv2.move_list.presentation

import me.androidbox.busbymoviesv2.move_list.domain.models.MovieResultModel
import me.androidbox.busbymoviesv2.move_list.presentation.models.MovieResult

data class MovieListState(
    var movieList: List<MovieResult> = emptyList(),
    var isLoading: Boolean = false,
    val isError: Boolean = false,
    val favouriteMovieCount: Int = 0,
    val pagingMovieList: MovieResultModel = MovieResultModel(),
)
