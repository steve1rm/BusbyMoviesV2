package me.androidbox.busbymoviesv2.movie_details.data.remote_data_source.imp

import BusbyMoviesV_.composeApp.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import me.androidbox.busbymoviesv2.core.data.network.Routes
import me.androidbox.busbymoviesv2.core.data.network.safeApiRequest
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.data.dto.MovieListDto
import me.androidbox.busbymoviesv2.movie_details.data.dto.CreditsDto
import me.androidbox.busbymoviesv2.movie_details.data.dto.MovieDetailDto
import me.androidbox.busbymoviesv2.movie_details.data.remote_data_source.MovieDetailRemoteDataSource

class MovieDetailRemoteDataSourceImp(private val httpClient: HttpClient) : MovieDetailRemoteDataSource {
    override suspend fun movieDetails(movieId: Int): CheckResult<MovieDetailDto, DataError.Network, ErrorModel> {
        val checkResult = safeApiRequest<MovieDetailDto> {

            val httpResponse = httpClient.get(urlString = Routes.MOVIE_DETAIL) {
                url {
                    /** https://api.themoviedb.org/3/movie/1184918 */
                    this.appendPathSegments(movieId.toString(), encodeSlash = true)
                }
                headers {
                    this.append("Authorization", "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN_AUTH}")
                    this.append("accept", ContentType.Application.Json.contentType)
                }
            }

            httpResponse
        }

        return checkResult
    }

    override suspend fun movieCredits(movieId: Int): CheckResult<CreditsDto, DataError.Network, ErrorModel> {
        val checkResult = safeApiRequest<CreditsDto> {
            val httpResponse = httpClient.get(urlString = Routes.MOVIE_DETAIL) {
                url {
                    this.appendPathSegments(movieId.toString(), "credits", encodeSlash = true)
                }
                headers {
                    this.append("Authorization", "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN_AUTH}")
                    this.append("accept", ContentType.Application.Json.contentType)
                }
            }

            httpResponse
        }

        return checkResult
    }

    override suspend fun similarMovies(movieId: Int): CheckResult<MovieListDto, DataError.Network, ErrorModel> {
        val checkResult = safeApiRequest<MovieListDto> {
            val httpResponse = httpClient.get(urlString = Routes.MOVIE_DETAIL) {
                url {
                    this.appendPathSegments(movieId.toString(), "similar", encodeSlash = true)
                }
                headers {
                    this.append("Authorization", "Bearer ${BuildConfig.TMDB_ACCESS_TOKEN_AUTH}")
                    this.append("accept", ContentType.Application.Json.contentType)
                }
            }

            httpResponse
        }

        return checkResult
    }
}
