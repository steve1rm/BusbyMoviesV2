package me.androidbox.busbymoviesv2.movie_details.data.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDetailDao {

    @Upsert
    suspend fun upsert(movieDetailEntity: MovieDetailEntity)

    @Delete
    suspend fun delete(movieDetailEntity: MovieDetailEntity)

    @Query("SELECT * FROM MovieDetailEntity")
    fun getAllMovies(): List<MovieDetailEntity>
}