package me.androidbox.busbymoviesv2.configuration.data.remote_data_source.imp

import BusbyMoviesV_.composeApp.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.client.request.headers
import me.androidbox.busbymoviesv2.configuration.data.dto.ConfigurationDto
import me.androidbox.busbymoviesv2.configuration.data.remote_data_source.ConfigurationRemoteDataSource
import me.androidbox.busbymoviesv2.core.data.network.Routes
import me.androidbox.busbymoviesv2.core.data.network.safeApiRequest
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel

class ConfigurationRemoteDataSourceImp(
    private val httpClient: HttpClient
) : ConfigurationRemoteDataSource {

    override suspend fun configuration(): CheckResult<ConfigurationDto, DataError.Network, ErrorModel> {
        val safeResult = safeApiRequest<ConfigurationDto> {
            val response = httpClient
                .get(Routes.CONFIGURATION) {
                headers {
                    this.append("Authorization", "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN_AUTH}")
                    this.append("accept", ContentType.Application.Json.contentType)
                }
            }

            response
        }

        return safeResult
    }
}