package me.androidbox.busbymoviesv2.configuration.data.repository

import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel

interface ConfigurationRepository {
    suspend fun configuration(): ConfigurationModel?
}