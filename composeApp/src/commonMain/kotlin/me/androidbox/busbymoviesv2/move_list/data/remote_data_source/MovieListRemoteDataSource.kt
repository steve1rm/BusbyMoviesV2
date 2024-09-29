package me.androidbox.busbymoviesv2.move_list.data.remote_data_source

import me.androidbox.busbymoviesv2.core.data.dto.ErrorResponseDto
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.data.dto.MovieListDto

interface MovieListRemoteDataSource {
    suspend fun nowPlaying(): CheckResult<MovieListDto, DataError.Network, ErrorModel>
    fun popular()
    fun trending()
    fun upcoming()
}