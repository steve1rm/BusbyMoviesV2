package me.androidbox.busbymoviesv2.movie_details.data.repository

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.CreditsModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieDetailModel

interface MovieDetailRepository {
    suspend fun movieDetail(movieId: Int): CheckResult<MovieDetailModel, DataError.Network, ErrorModel>
    suspend fun movieCast(movieId: Int): CheckResult<CreditsModel, DataError.Network, ErrorModel>
}