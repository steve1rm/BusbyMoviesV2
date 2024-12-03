package me.androidbox.busbymoviesv2.movie_details.presentation

import me.androidbox.busbymoviesv2.move_list.presentation.models.MovieResult
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Credits
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Videos

data class MovieDetailState(
    val movieDetail: MovieDetail = MovieDetail(),
    val listOfMovieDetails: List<MovieResult> = emptyList(),
    val otherVideoTrailers: List<Videos> = emptyList(),
    val movieCredits: Credits = Credits(),
    val isLoadingDetails: Boolean = false,
    val isLoadingSimilarMovies: Boolean = false,
    val isLoadingCredits: Boolean = false,
    val isSavingFavourite: Boolean = false, /** saving is in progress */
    val hasSavedFavourite: Boolean = false  /** saved has completed */
)
