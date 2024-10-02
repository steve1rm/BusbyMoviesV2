package me.androidbox.busbymoviesv2.configuration.domain.usecases

import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel

fun interface ConfigurationUseCase {
    suspend fun execute(): ConfigurationModel?
}