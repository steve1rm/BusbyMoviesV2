package me.androidbox.busbymoviesv2.configuration.data.repository.imp

import me.androidbox.busbymoviesv2.configuration.data.remote_data_source.ConfigurationRemoteDataSource
import me.androidbox.busbymoviesv2.configuration.data.repository.ConfigurationRepository
import me.androidbox.busbymoviesv2.configuration.data.toConfigurationModel
import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult

class ConfigurationRepositoryImp(
    private val configurationRemoteDataSource: ConfigurationRemoteDataSource
) : ConfigurationRepository {

    override suspend fun configuration(): ConfigurationModel? {
        return when(val checkResult = configurationRemoteDataSource.configuration()) {
            is CheckResult.Failure -> {
               null
            }
            is CheckResult.Success -> {
                checkResult.data.toConfigurationModel()
            }
        }
    }
}