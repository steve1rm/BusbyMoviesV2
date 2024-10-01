package me.androidbox.busbymoviesv2.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js
import org.koin.dsl.module

val wasmSpecificModule = module {
    single<HttpClientEngine> {
        HttpClient(Js).engine
    }
}