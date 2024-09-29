package me.androidbox.busbymoviesv2.move_list.data.repository

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel

interface MovieListRepository {
    suspend fun nowPlaying(): CheckResult<MovieListModel, DataError.Network, ErrorModel>
    suspend fun popular()
    suspend fun trending()
    suspend fun upcoming()
}