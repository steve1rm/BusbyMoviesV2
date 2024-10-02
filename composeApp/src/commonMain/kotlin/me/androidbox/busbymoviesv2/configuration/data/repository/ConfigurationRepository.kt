package me.androidbox.busbymoviesv2.configuration.data.repository

import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel

interface ConfigurationRepository {
    suspend fun configuration(): CheckResult<ConfigurationModel, DataError.Network, ErrorModel>
}