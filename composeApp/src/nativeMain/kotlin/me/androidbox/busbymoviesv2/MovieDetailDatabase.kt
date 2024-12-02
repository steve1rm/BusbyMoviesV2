package me.androidbox.busbymoviesv2

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import me.androidbox.busbymoviesv2.movie_details.data.entities.MovieDetailDatabase

fun getMovieDetailDatabase(): MovieDetailDatabase {
    val dbFile = NSHomeDirectory() + "/movieDetail.db"

    return Room.databaseBuilder<MovieDetailDatabase>(
        name = dbFile,
        factory = {
            MovieDetailDatabase::class.instantiateImpl()
        }
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}