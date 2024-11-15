package me.androidbox.busbymoviesv2.movie_details.presentation

import me.androidbox.busbymoviesv2.move_list.presentation.models.MovieResult
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Credits
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail

data class MovieDetailState(
    val movieDetail: MovieDetail = MovieDetail(),
    val listOfMovieDetails: List<MovieResult> = emptyList(),
    val movieCredits: Credits = Credits(),
    val isLoadingDetails: Boolean = false,
    val isLoadingSimilarMovies: Boolean = false,
    val isLoadingCredits: Boolean = false
)
