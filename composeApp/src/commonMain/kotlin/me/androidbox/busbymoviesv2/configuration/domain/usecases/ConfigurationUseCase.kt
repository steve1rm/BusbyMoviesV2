package me.androidbox.busbymoviesv2.configuration.domain.usecases

import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel

fun interface ConfigurationUseCase {
    suspend fun execute(): CheckResult<ConfigurationModel, DataError.Network, ErrorModel>
}