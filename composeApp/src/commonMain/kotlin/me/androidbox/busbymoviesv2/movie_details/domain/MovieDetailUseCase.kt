package me.androidbox.busbymoviesv2.movie_details.domain

fun interface MovieDetailUseCase {
    suspend fun execute(movieId: Int)
}