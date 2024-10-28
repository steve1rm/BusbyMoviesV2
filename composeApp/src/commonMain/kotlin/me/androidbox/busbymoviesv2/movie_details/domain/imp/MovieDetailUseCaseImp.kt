package me.androidbox.busbymoviesv2.movie_details.domain.imp

import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.domain.MovieDetailUseCase

class MovieDetailUseCaseImp(private val movieDetailRepository: MovieDetailRepository) : MovieDetailUseCase {
    override suspend fun execute(movieId: Int) {
        movieDetailRepository.movieDetail(movieId)
    }
}