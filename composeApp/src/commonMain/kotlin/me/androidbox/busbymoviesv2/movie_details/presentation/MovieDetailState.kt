package me.androidbox.busbymoviesv2.movie_details.presentation

import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail

data class MovieDetailState(
    val movieDetail: MovieDetail = MovieDetail(),
    val isLoading: Boolean = false
)
