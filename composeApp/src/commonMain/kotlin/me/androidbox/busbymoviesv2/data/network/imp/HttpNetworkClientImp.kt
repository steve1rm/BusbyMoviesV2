package me.androidbox.busbymoviesv2.data.network.imp

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.androidbox.busbymoviesv2.data.network.HttpNetworkClient


class HttpNetworkClientImp(
    private val httpClientEngine: HttpClientEngine
) : HttpNetworkClient {

    override fun build(): HttpClient {
        val httpClient = HttpClient(httpClientEngine) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.d {
                            message
                        }
                    }
                }

                this.level = LogLevel.ALL
            }

        }

        return httpClient
    }
}