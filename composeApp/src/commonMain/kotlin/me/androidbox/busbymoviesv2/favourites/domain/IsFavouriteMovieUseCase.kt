package me.androidbox.busbymoviesv2.favourites.domain

fun interface IsFavouriteMovieUseCase {
    suspend fun execute(movieId: Int): Boolean
}