package me.androidbox.busbymoviesv2.move_list.di

import me.androidbox.busbymoviesv2.move_list.data.repository.MovieListRepository
import me.androidbox.busbymoviesv2.move_list.domain.usecases.MovieListNowPlayingUseCase
import me.androidbox.busbymoviesv2.move_list.domain.usecases.imp.MovieListNowPlayingUseCaseImp
import org.koin.dsl.module

val movieListDomainModule = module {
    factory<MovieListNowPlayingUseCase> {
        MovieListNowPlayingUseCaseImp(get<MovieListRepository>())
    }
}