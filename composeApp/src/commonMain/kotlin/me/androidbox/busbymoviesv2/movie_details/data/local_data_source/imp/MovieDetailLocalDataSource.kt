package me.androidbox.busbymoviesv2.movie_details.data.local_data_source.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

interface MovieDetailLocalDataSource {
    suspend fun insertFavouriteMovie(movieFavouriteModel: MovieFavouriteModel)

    suspend fun deleteFavouriteMovie(movieFavouriteModel: MovieFavouriteModel)

    fun getAllFavouriteMovies(): Flow<List<MovieFavouriteModel>>

    suspend fun isFavouriteMovie(movieId: Int): Boolean
}