package me.androidbox.busbymoviesv2.movie_details.data.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailDao {

    @Upsert
    suspend fun upsertFavourite(movieFavouriteEntity: MovieFavouriteEntity)

    @Delete
    suspend fun deleteFavourite(movieFavouriteEntity: MovieFavouriteEntity)

    @Query("SELECT * FROM MovieFavouriteEntity")
    fun getAllFavouriteMovies(): Flow<List<MovieFavouriteEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM MovieFavouriteEntity WHERE id = :id)")
    suspend fun isFavouriteMovie(id: Int): Boolean
}