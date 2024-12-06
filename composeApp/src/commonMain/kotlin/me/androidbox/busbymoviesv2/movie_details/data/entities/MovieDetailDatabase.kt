package me.androidbox.busbymoviesv2.movie_details.data.entities

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieFavouriteEntity::class],
    version = 1
)
abstract class MovieDetailDatabase : RoomDatabase() {
    abstract fun movieDetailDao(): MovieDetailDao
}