package me.androidbox.busbymoviesv2.movie_details.domain.usecase

fun interface IsFavouriteMovieUseCase {
    suspend fun execute(movieId: Int): Boolean
}