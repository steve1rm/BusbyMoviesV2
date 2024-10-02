package me.androidbox.busbymoviesv2.configuration.data.repository.imp

import me.androidbox.busbymoviesv2.configuration.data.dto.ConfigurationDto
import me.androidbox.busbymoviesv2.configuration.data.remote_data_source.ConfigurationRemoteDataSource
import me.androidbox.busbymoviesv2.configuration.data.repository.ConfigurationRepository
import me.androidbox.busbymoviesv2.configuration.data.toConfigurationModel
import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel

class ConfigurationRepositoryImp(
    private val configurationRemoteDataSource: ConfigurationRemoteDataSource
) : ConfigurationRepository {

    override suspend fun configuration(): CheckResult<ConfigurationModel, DataError.Network, ErrorModel> {
        val checkResult = configurationRemoteDataSource.configuration()

        return when(checkResult) {
            is CheckResult.Failure -> {
               CheckResult.Failure(checkResult.exceptionError, checkResult.responseError)
            }
            is CheckResult.Success -> {
                CheckResult.Success(checkResult.data.toConfigurationModel())
            }
        }
    }
}