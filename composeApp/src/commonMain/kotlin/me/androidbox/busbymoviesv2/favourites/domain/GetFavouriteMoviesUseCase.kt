package me.androidbox.busbymoviesv2.favourites.domain

import kotlinx.coroutines.flow.Flow
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

fun interface GetFavouriteMoviesUseCase {
    fun execute(): Flow<List<MovieFavouriteModel>>
}