package me.androidbox.busbymoviesv2.move_list.data.remote_data_source.imp

import BusbyMoviesV_.composeApp.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import me.androidbox.busbymoviesv2.core.data.dto.ErrorResponseDto
import me.androidbox.busbymoviesv2.core.data.network.Routes
import me.androidbox.busbymoviesv2.core.data.network.safeApiRequest
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.data.remote_data_source.MovieListRemoteDataSource
import me.androidbox.busbymoviesv2.move_list.data.dto.MovieListDto

class MovieListRemoteDataSourceImp(
    private val httpClient: HttpClient
) : MovieListRemoteDataSource {
    override suspend fun nowPlaying(): CheckResult<MovieListDto, DataError.Network, ErrorModel> {
        val safeResult = safeApiRequest<MovieListDto> {
            val response = httpClient
                .get(Routes.NOW_PLAYING) {
                    headers {
                        this.append("Authorization", "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN_AUTH}")
                        this.append("accept", ContentType.Application.Json.contentType)
                    }
                }

            println("NETWORK STATUS ${response.status}")
            response
        }

        return safeResult
    }

    override fun popular() {
        TODO("Not yet implemented")
    }

    override fun trending() {
        TODO("Not yet implemented")
    }

    override fun upcoming() {
        TODO("Not yet implemented")
    }
}