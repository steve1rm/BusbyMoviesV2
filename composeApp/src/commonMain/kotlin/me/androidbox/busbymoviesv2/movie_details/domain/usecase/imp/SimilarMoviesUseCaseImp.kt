package me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.Error
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.SimilarMoviesUseCase

class SimilarMoviesUseCaseImp(private val movieDetailRepository: MovieDetailRepository) : SimilarMoviesUseCase {
    override suspend fun execute(movieId: Int): CheckResult<MovieListModel, Error, ErrorModel> {
        return movieDetailRepository.similarMovies(movieId)
    }
}