package me.androidbox.busbymoviesv2.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import me.androidbox.busbymoviesv2.movie_details.data.entities.MovieDetailDao
import me.androidbox.busbymoviesv2.movie_details.data.entities.MovieDetailDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single<HttpClientEngine> {
        /** As we are using specific engines,
         * might as well use the specific ones for each platform i.e android, iso, desktop, js */
        HttpClient(CIO).engine
    }

    single<MovieDetailDao> {
        val dbFile = androidContext().getDatabasePath("movieDetail.db")

        val database = Room.databaseBuilder<MovieDetailDatabase>(
            context = androidContext(),
            name = dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .build()

        database.movieDetailDao()
    }
}