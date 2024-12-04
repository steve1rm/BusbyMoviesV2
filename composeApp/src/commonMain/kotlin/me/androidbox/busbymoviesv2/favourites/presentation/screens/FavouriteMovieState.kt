package me.androidbox.busbymoviesv2.favourites.presentation.screens

import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

data class FavouriteMovieState(
    val listOfFavouriteMovies: List<MovieFavouriteModel> = emptyList()
)
