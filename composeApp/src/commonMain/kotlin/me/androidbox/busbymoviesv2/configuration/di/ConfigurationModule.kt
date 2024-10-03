package me.androidbox.busbymoviesv2.configuration.di

import me.androidbox.busbymoviesv2.configuration.data.remote_data_source.ConfigurationRemoteDataSource
import me.androidbox.busbymoviesv2.configuration.data.remote_data_source.imp.ConfigurationRemoteDataSourceImp
import me.androidbox.busbymoviesv2.configuration.data.repository.ConfigurationRepository
import me.androidbox.busbymoviesv2.configuration.data.repository.imp.ConfigurationRepositoryImp
import me.androidbox.busbymoviesv2.configuration.domain.usecases.ConfigurationUseCase
import me.androidbox.busbymoviesv2.configuration.domain.usecases.imp.ConfigurationUseCaseImp
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val configurationModule = module {
    factoryOf(::ConfigurationUseCaseImp).bind(ConfigurationUseCase::class)

    factoryOf(::ConfigurationRemoteDataSourceImp).bind<ConfigurationRemoteDataSource>()

    factoryOf(::ConfigurationRepositoryImp).bind(ConfigurationRepository::class)
}
