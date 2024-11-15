package me.androidbox.busbymoviesv2.movie_details.data.repository.imp

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.data.toMovieListModel
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel
import me.androidbox.busbymoviesv2.movie_details.data.remote_data_source.MovieDetailRemoteDataSource
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.data.toCreditsModel
import me.androidbox.busbymoviesv2.movie_details.data.toMovieDetailModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.CreditsModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieDetailModel

class MovieDetailRepositoryImp(private val movieDetailRemoteDataSource: MovieDetailRemoteDataSource) : MovieDetailRepository {
    override suspend fun movieDetail(movieId: Int): CheckResult<MovieDetailModel, DataError.Network, ErrorModel> {
        return when(val checkResult = movieDetailRemoteDataSource.movieDetails(movieId)) {
            is CheckResult.Failure -> {
                CheckResult.Failure(checkResult.exceptionError, checkResult.responseError)
            }
            is CheckResult.Success -> {
                CheckResult.Success(checkResult.data.toMovieDetailModel())
            }
        }
    }

    override suspend fun movieCast(movieId: Int): CheckResult<CreditsModel, DataError.Network, ErrorModel> {
        return when(val checkResult = movieDetailRemoteDataSource.movieCredits(movieId)) {
            is CheckResult.Failure -> {
                CheckResult.Failure(checkResult.exceptionError, checkResult.responseError)
            }
            is CheckResult.Success -> {
                CheckResult.Success(checkResult.data.toCreditsModel())
            }
        }
    }

    override suspend fun similarMovies(movieId: Int): CheckResult<MovieListModel, DataError.Network, ErrorModel> {
        return when(val checkResult = movieDetailRemoteDataSource.similarMovies(movieId)) {
            is CheckResult.Failure -> {
                CheckResult.Failure(checkResult.exceptionError, checkResult.responseError)
            }
            is CheckResult.Success -> {
                CheckResult.Success(checkResult.data.toMovieListModel())
            }
        }
    }
}