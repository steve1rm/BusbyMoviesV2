package me.androidbox.busbymoviesv2.favourites.domain.imp

import me.androidbox.busbymoviesv2.favourites.domain.IsFavouriteMovieUseCase
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository

class IsFavouriteMovieUseCaseImp(private val movieDetailRepository: MovieDetailRepository) :
    IsFavouriteMovieUseCase {
    override suspend fun execute(movieId: Int): Boolean {
        return movieDetailRepository.isFavouriteMovie(movieId)
    }
}