package me.androidbox.busbymoviesv2.configuration.data.remote_data_source

import me.androidbox.busbymoviesv2.configuration.data.dto.ConfigurationDto
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel

interface ConfigurationRemoteDataSource {
    suspend fun configuration(): CheckResult<ConfigurationDto, DataError.Network, ErrorModel>
}