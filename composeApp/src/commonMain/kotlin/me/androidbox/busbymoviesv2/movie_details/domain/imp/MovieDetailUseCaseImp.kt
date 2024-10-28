package me.androidbox.busbymoviesv2.movie_details.domain.imp

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.domain.MovieDetailUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieDetailModel

class MovieDetailUseCaseImp(private val movieDetailRepository: MovieDetailRepository) : MovieDetailUseCase {
    override suspend fun execute(movieId: Int): CheckResult<MovieDetailModel, DataError.Network, ErrorModel> {
        return movieDetailRepository.movieDetail(movieId)
    }
}