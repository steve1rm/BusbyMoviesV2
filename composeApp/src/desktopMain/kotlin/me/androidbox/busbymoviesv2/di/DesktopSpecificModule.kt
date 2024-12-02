package me.androidbox.busbymoviesv2.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import me.androidbox.busbymoviesv2.movie_details.data.entities.MovieDetailDatabase
import org.koin.dsl.module
import java.io.File

val desktopSpecificModule = module {
    single<HttpClientEngine> {
        HttpClient(CIO).engine
    }

    single<MovieDetailDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "movieDetail.db.db")

        Room.databaseBuilder<MovieDetailDatabase>(
            name = dbFile.absolutePath)
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}