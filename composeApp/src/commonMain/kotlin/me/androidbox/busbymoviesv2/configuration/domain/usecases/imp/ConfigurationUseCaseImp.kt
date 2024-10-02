package me.androidbox.busbymoviesv2.configuration.domain.usecases.imp

import me.androidbox.busbymoviesv2.configuration.data.repository.ConfigurationRepository
import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import me.androidbox.busbymoviesv2.configuration.domain.usecases.ConfigurationUseCase

class ConfigurationUseCaseImp(
    private val configurationRepository: ConfigurationRepository
) : ConfigurationUseCase {
    override suspend fun execute(): ConfigurationModel? {
        return configurationRepository.configuration()
    }
}
