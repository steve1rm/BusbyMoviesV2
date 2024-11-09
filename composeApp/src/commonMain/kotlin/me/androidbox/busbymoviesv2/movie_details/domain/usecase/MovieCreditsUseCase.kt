package me.androidbox.busbymoviesv2.movie_details.domain.usecase

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.CreditsModel

fun interface MovieCreditsUseCase {
    suspend fun execute(movieId: Int): CheckResult<CreditsModel, DataError.Network, ErrorModel>
}