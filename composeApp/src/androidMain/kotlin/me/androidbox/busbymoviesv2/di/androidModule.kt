package me.androidbox.busbymoviesv2.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val androidModule = module {
    single<HttpClientEngine> {
        /** As we are using specific engines,
         * might as well use the specific ones for each platform i.e android, iso, desktop, js */
        HttpClient(CIO).engine
    }
}