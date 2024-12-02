package me.androidbox.busbymoviesv2

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import me.androidbox.busbymoviesv2.movie_details.data.entities.MovieDetailDatabase

fun getMovieDetailDatabase(context: Context): MovieDetailDatabase {
    val dbFile = context.getDatabasePath("movieDetail.db")

    return Room.databaseBuilder<MovieDetailDatabase>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .setDriver(BundledSQLiteDriver())
        .build()
}