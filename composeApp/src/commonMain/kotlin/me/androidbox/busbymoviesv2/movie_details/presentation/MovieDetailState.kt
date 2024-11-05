package me.androidbox.busbymoviesv2.movie_details.presentation

import me.androidbox.busbymoviesv2.movie_details.presentation.model.Credits
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail

data class MovieDetailState(
    val movieDetail: MovieDetail = MovieDetail(),
    val movieCredits: Credits = Credits(),
    val isLoadingDetails: Boolean = false,
    val isLoadingCredits: Boolean = false
)
