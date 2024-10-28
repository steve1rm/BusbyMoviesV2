package me.androidbox.busbymoviesv2.movie_details.data.repository.imp

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.movie_details.data.MovieDetailDto
import me.androidbox.busbymoviesv2.movie_details.data.remote_data_source.MovieDetailRemoteDataSource
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository

class MovieDetailRepositoryImp(private val movieDetailRemoteDataSource: MovieDetailRemoteDataSource) : MovieDetailRepository {
    override suspend fun movieDetail(movieId: Int): CheckResult<MovieDetailDto, DataError.Network, ErrorModel> {
        return when(val checkResult = movieDetailRemoteDataSource.movieDetails(movieId)) {
            is CheckResult.Failure -> {
                CheckResult.Failure(checkResult.exceptionError, checkResult.responseError)
            }
            is CheckResult.Success -> {
                CheckResult.Success(checkResult.data)
            }
        }
    }
}