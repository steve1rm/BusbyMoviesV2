package me.androidbox.busbymoviesv2.movie_details.data.repository

import kotlinx.coroutines.flow.Flow
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.CreditsModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieDetailModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

interface MovieDetailRepository {
    suspend fun movieDetail(movieId: Int): CheckResult<MovieDetailModel, DataError.Network, ErrorModel>
    suspend fun movieCast(movieId: Int): CheckResult<CreditsModel, DataError.Network, ErrorModel>
    suspend fun similarMovies(movieId: Int): CheckResult<MovieListModel, DataError.Network, ErrorModel>

    suspend fun insertFavouriteMovie(movieFavouriteModel: MovieFavouriteModel)
    suspend fun deleteFavouriteMovie(movieFavouriteModel: MovieFavouriteModel)

    suspend fun isFavouriteMovie(movieId: Int): Boolean
    fun getAllFavouriteMovies(): Flow<List<MovieFavouriteModel>>
}