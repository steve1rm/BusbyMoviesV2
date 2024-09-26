package me.androidbox.busbymoviesv2.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val androidModule = module {
    single<HttpClientEngine> {
        HttpClient(CIO).engine
    }
}