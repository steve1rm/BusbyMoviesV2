package me.androidbox.busbymoviesv2.move_list.data.di

import io.ktor.client.HttpClient
import me.androidbox.busbymoviesv2.move_list.data.remote_data_source.MovieListRemoteDataSource
import me.androidbox.busbymoviesv2.move_list.data.remote_data_source.imp.MovieListRemoteDataSourceImp
import org.koin.dsl.module

val movieListDataModule = module {

    factory<MovieListRemoteDataSource> {
        MovieListRemoteDataSourceImp(get<HttpClient>())
    }
}