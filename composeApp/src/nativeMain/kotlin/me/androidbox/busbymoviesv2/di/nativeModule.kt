package me.androidbox.busbymoviesv2.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import me.androidbox.busbymoviesv2.movie_details.data.entities.MovieDetailDatabase
import org.koin.dsl.module

val nativeModule = module {
    single<HttpClientEngine> {
        /** As we are using specific engines,
         * might as well use the specific ones for each platform i.e android, iso, desktop, js */
        HttpClient(Darwin).engine
    }

    single<MovieDetailDatabase> {
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
}