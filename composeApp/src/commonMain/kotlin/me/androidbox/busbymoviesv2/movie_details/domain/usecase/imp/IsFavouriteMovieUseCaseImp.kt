package me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp

import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.IsFavouriteMovieUseCase

class IsFavouriteMovieUseCaseImp(private val movieDetailRepository: MovieDetailRepository) : IsFavouriteMovieUseCase {
    override suspend fun execute(movieId: Int): Boolean {
        return movieDetailRepository.isFavouriteMovie(movieId)
    }
}