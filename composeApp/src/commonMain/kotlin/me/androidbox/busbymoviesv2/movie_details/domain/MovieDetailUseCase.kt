package me.androidbox.busbymoviesv2.movie_details.domain

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieDetailModel

fun interface MovieDetailUseCase {
    suspend fun execute(movieId: Int): CheckResult<MovieDetailModel, DataError.Network, ErrorModel>
}