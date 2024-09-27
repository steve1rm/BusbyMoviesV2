package me.androidbox.busbymoviesv2.move_list.data.di

import io.ktor.client.HttpClient
import me.androidbox.busbymoviesv2.move_list.data.remote_data_source.MoveListRemoteDataSource
import me.androidbox.busbymoviesv2.move_list.data.remote_data_source.imp.MoveListRemoteDataSourceImp
import org.koin.dsl.module

val movieListDataModule = module {

    factory<MoveListRemoteDataSource> {
        MoveListRemoteDataSourceImp(get<HttpClient>())
    }
}