package me.androidbox.busbymoviesv2.core.data.network

import io.ktor.client.HttpClient

interface HttpNetworkClient {
    fun build(): HttpClient
}