package me.androidbox.busbymoviesv2.move_list.data.remote_data_source

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.data.dto.MovieListDto

interface MovieListRemoteDataSource {
    suspend fun movieList(page: Int, movieRoute: String): CheckResult<MovieListDto, DataError.Network, ErrorModel>
}