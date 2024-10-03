package me.androidbox.busbymoviesv2.move_list.domain.usecases

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel

fun interface MovieListNowPlayingUseCase {
    suspend fun execute(): CheckResult<MovieListModel, DataError.Network, ErrorModel>
}