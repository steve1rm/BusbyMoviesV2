package me.androidbox.busbymoviesv2.move_list.data.remote_data_source.imp

import BusbyMoviesV_.composeApp.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import kotlinx.coroutines.delay
import me.androidbox.busbymoviesv2.core.data.network.safeApiRequest
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.data.dto.MovieListDto
import me.androidbox.busbymoviesv2.move_list.data.remote_data_source.MovieListRemoteDataSource

class MovieListRemoteDataSourceImp(
    private val httpClient: HttpClient
) : MovieListRemoteDataSource {
    override suspend fun movieList(page: Int, movieRoute: String): CheckResult<MovieListDto, DataError.Network, ErrorModel> {
        val safeResult = safeApiRequest<MovieListDto> {
            delay(2_000L)
            val response = httpClient
                .get(movieRoute) {
                    parameter("page", page)
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