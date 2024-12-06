package me.androidbox.busbymoviesv2.movie_details.domain.usecase

import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

fun interface DeleteFavouriteMovieUseCase {
    suspend fun execute(movieFavouriteModel: MovieFavouriteModel)
}