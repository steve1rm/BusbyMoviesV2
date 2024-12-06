package me.androidbox.busbymoviesv2.movie_details.data.repository.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.data.toMovieListModel
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel
import me.androidbox.busbymoviesv2.movie_details.data.local_data_source.imp.MovieDetailLocalDataSource
import me.androidbox.busbymoviesv2.movie_details.data.remote_data_source.MovieDetailRemoteDataSource
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.data.toCreditsModel
import me.androidbox.busbymoviesv2.movie_details.data.toMovieDetailModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.CreditsModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieDetailModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

class MovieDetailRepositoryImp(
    private val movieDetailRemoteDataSource: MovieDetailRemoteDataSource,
    private val movieDetailLocalDataSource: MovieDetailLocalDataSource) : MovieDetailRepository {
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

    override suspend fun insertFavouriteMovie(movieFavouriteModel: MovieFavouriteModel) {
        movieDetailLocalDataSource.insertFavouriteMovie(movieFavouriteModel)
    }

    override suspend fun deleteFavouriteMovie(movieFavouriteModel: MovieFavouriteModel) {
        movieDetailLocalDataSource.deleteFavouriteMovie(movieFavouriteModel)
    }

    override fun getAllFavouriteMovies(): Flow<List<MovieFavouriteModel>> {
        return movieDetailLocalDataSource.getAllFavouriteMovies()
    }

    override suspend fun isFavouriteMovie(movieId: Int): Boolean {
        return movieDetailLocalDataSource.isFavouriteMovie(movieId)
    }
}