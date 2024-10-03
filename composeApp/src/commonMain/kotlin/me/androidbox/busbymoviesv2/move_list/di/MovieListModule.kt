package me.androidbox.busbymoviesv2.move_list.di

import me.androidbox.busbymoviesv2.configuration.domain.usecases.ConfigurationUseCase
import me.androidbox.busbymoviesv2.move_list.data.repository.MovieListRepository
import me.androidbox.busbymoviesv2.move_list.domain.usecases.MovieListNowPlayingUseCase
import me.androidbox.busbymoviesv2.move_list.domain.usecases.imp.MovieListNowPlayingUseCaseImp
import me.androidbox.busbymoviesv2.move_list.presentation.MoveListViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieListModule = module {
    factory<MovieListNowPlayingUseCase> {
        MovieListNowPlayingUseCaseImp(get<MovieListRepository>())
    }

    // Alternative approach
    // factoryOf(::MovieListNowPlayingUseCaseImp).bind(MovieListNowPlayingUseCase::class)

    viewModel {
        MoveListViewModel(get<MovieListNowPlayingUseCase>(), get<ConfigurationUseCase>())
    }
}