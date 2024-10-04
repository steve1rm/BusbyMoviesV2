package me.androidbox.busbymoviesv2.move_list.data.repository.imp

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.data.remote_data_source.MovieListRemoteDataSource
import me.androidbox.busbymoviesv2.move_list.data.repository.MovieListRepository
import me.androidbox.busbymoviesv2.move_list.data.toMovieListModel
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel

class MovieListRepositoryImp(
    private val movieListRemoteDataSource: MovieListRemoteDataSource
): MovieListRepository {

    override suspend fun nowPlaying(movieRoute: String): CheckResult<MovieListModel, DataError.Network, ErrorModel> {
        val checkResult = when(val response = movieListRemoteDataSource.nowPlaying(movieRoute)) {
            is CheckResult.Failure -> {
                CheckResult.Failure(
                    exceptionError = response.exceptionError,
                    responseError = ErrorModel(
                        statusCode = response.responseError?.statusCode ?: 0,
                        statusMessage = response.responseError?.statusMessage.orEmpty(),
                        success = response.responseError?.success ?: false
                    )
                )
            }
            is CheckResult.Success -> {
                CheckResult.Success(
                    data = response.data.toMovieListModel()
                )
            }
        }

        return checkResult
    }

    override suspend fun popular() {
        TODO("Not yet implemented")
    }

    override suspend fun trending() {
        TODO("Not yet implemented")
    }

    override suspend fun upcoming() {
        TODO("Not yet implemented")
    }
}