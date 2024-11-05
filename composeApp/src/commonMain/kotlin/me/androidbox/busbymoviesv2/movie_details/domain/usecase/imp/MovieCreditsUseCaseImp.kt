package me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.domain.models.CreditsModel
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.MovieCreditsUseCase

class MovieCreditsUseCaseImp(private val movieDetailRepository: MovieDetailRepository) : MovieCreditsUseCase {
    override suspend fun execute(movieId: Int): CheckResult<CreditsModel, DataError.Network, ErrorModel> {
        return movieDetailRepository.movieCast(movieId)
    }
}