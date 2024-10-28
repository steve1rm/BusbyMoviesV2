package me.androidbox.busbymoviesv2.movie_details.data.remote_data_source

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.movie_details.data.MovieDetailDto

interface MovieDetailRemoteDataSource {
    suspend fun movieDetails(movieId: Int): CheckResult<MovieDetailDto, DataError.Network, ErrorModel>
}