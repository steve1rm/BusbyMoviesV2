package me.androidbox.busbymoviesv2.movie_details.di

import io.ktor.client.HttpClient
import me.androidbox.busbymoviesv2.movie_details.data.remote_data_source.MovieDetailRemoteDataSource
import me.androidbox.busbymoviesv2.movie_details.data.remote_data_source.imp.MovieDetailRemoteDataSourceImp
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.data.repository.imp.MovieDetailRepositoryImp
import me.androidbox.busbymoviesv2.movie_details.domain.MovieDetailUseCase
import me.androidbox.busbymoviesv2.movie_details.domain.imp.MovieDetailUseCaseImp
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val movieDetailModule = module {
    factory<MovieDetailUseCase> {
        MovieDetailUseCaseImp(get<MovieDetailRepository>())
    }

    factory<MovieDetailRepository> {
        MovieDetailRepositoryImp(get<MovieDetailRemoteDataSource>())
    }

    factory<MovieDetailRemoteDataSource> {
        MovieDetailRemoteDataSourceImp(get<HttpClient>())
    }

    viewModelOf(::MovieDetailViewModel)
}