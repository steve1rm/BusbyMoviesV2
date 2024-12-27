package me.androidbox.busbymoviesv2.favourites.domain

import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

fun interface DeleteFavouriteMovieUseCase {
    suspend fun execute(movieFavouriteModel: MovieFavouriteModel)
}