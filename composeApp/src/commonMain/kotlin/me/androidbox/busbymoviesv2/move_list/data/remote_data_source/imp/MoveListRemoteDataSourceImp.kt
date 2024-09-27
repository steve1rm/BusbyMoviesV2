package me.androidbox.busbymoviesv2.move_list.data.remote_data_source.imp

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import me.androidbox.busbymoviesv2.core.data.network.Routes
import me.androidbox.busbymoviesv2.core.data.network.safeApiRequest
import me.androidbox.busbymoviesv2.move_list.data.remote_data_source.MoveListRemoteDataSource
import me.androidbox.busbymoviesv2.move_list.dto.MoveListDto

class MoveListRemoteDataSourceImp(
    private val httpClient: HttpClient
) : MoveListRemoteDataSource {
    override suspend fun nowPlaying() {
        val safeResult = safeApiRequest<MoveListDto> {
            println("NETWORK STATUS")
            val response = httpClient
                .get(Routes.NOW_PLAYING) {
                    headers {
                        this.append("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NDhjNjlhMDFmNzc3YjdmMTZkYjg4YjA1M2JiYzkwMyIsIm5iZiI6MTcyNzI3ODc0MS4zOTE3NTEsInN1YiI6IjU3ZmU4ZWMzYzNhMzY4MWUyOTAwOWIyMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6ieKdSf2JWGpYSA60UNeFTSVdRX_w538qYNO7X8qVQI")
                        this.append("accept", ContentType.Application.Json.contentType)
                    }
                }

            println("NETWORK STATUS ${response.status}")
            response
        }
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