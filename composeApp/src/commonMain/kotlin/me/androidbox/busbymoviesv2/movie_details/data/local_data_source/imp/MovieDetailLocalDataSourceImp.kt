package me.androidbox.busbymoviesv2.movie_details.data.local_data_source.imp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.androidbox.busbymoviesv2.movie_details.data.entities.MovieDetailDao
import me.androidbox.busbymoviesv2.movie_details.data.toMovieFavouriteEntity
import me.androidbox.busbymoviesv2.movie_details.data.toMovieFavouriteModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

class MovieDetailLocalDataSourceImp(
    private val movieDetailDao: MovieDetailDao
) : MovieDetailLocalDataSource {
    override suspend fun insertFavouriteMovie(movieFavouriteModel: MovieFavouriteModel) {
        movieDetailDao.upsertFavourite(movieFavouriteModel.toMovieFavouriteEntity())
    }

    override suspend fun deleteFavouriteMovie(movieFavouriteModel: MovieFavouriteModel) {
        movieDetailDao.deleteFavourite(movieFavouriteModel.toMovieFavouriteEntity())
    }

    override fun getAllFavouriteMovies(): Flow<List<MovieFavouriteModel>> {
        return movieDetailDao.getAllFavouriteMovies().map { movieFavouriteEntities ->
            movieFavouriteEntities.map { movieFavouriteEntity ->
                movieFavouriteEntity.toMovieFavouriteModel()
            }
        }
    }
}