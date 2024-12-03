package me.androidbox.busbymoviesv2.movie_details.data.local_data_source.imp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.androidbox.busbymoviesv2.movie_details.data.entities.MovieDetailDatabase
import me.androidbox.busbymoviesv2.movie_details.data.toMovieFavouriteEntity
import me.androidbox.busbymoviesv2.movie_details.data.toMovieFavouriteModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

class MovieDetailLocalDataSourceImp(
    private val movieDetailDatabase: MovieDetailDatabase
) : MovieDetailLocalDataSource {
    override suspend fun insertFavouriteMovie(movieFavouriteModel: MovieFavouriteModel) {
        movieDetailDatabase.movieDetailDao().upsertFavourite(movieFavouriteModel.toMovieFavouriteEntity())
    }

    override suspend fun deleteFavouriteMovie(movieFavouriteModel: MovieFavouriteModel) {
        movieDetailDatabase.movieDetailDao().deleteFavourite(movieFavouriteModel.toMovieFavouriteEntity())
    }

    override fun getAllFavouriteMovies(): Flow<List<MovieFavouriteModel>> {
        return movieDetailDatabase.movieDetailDao().getAllFavouriteMovies().map { movieFavouriteEntities ->
            movieFavouriteEntities.map { movieFavouriteEntity ->
                movieFavouriteEntity.toMovieFavouriteModel()
            }
        }
    }
}