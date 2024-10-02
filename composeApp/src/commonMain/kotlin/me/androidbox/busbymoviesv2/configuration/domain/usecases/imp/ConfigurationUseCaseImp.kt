package me.androidbox.busbymoviesv2.configuration.domain.usecases.imp

import me.androidbox.busbymoviesv2.configuration.data.repository.ConfigurationRepository
import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import me.androidbox.busbymoviesv2.configuration.domain.usecases.ConfigurationUseCase
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel

class ConfigurationUseCaseImp(
    private val configurationRepository: ConfigurationRepository
) : ConfigurationUseCase {
    override suspend fun execute(): CheckResult<ConfigurationModel, DataError.Network, ErrorModel> {
        return configurationRepository.configuration()
    }
}
