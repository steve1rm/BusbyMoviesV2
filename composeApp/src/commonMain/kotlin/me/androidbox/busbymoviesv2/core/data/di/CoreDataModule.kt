package me.androidbox.busbymoviesv2.core.data.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import me.androidbox.busbymoviesv2.core.data.network.imp.HttpNetworkClientImp
import org.koin.dsl.module

val coreDataModule = module {
    single<HttpClient> {
        HttpNetworkClientImp(get<HttpClientEngine>()).build()
    }

    single<String> {
        "This is has been initialized - koin is working"
    }
}