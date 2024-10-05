package me.androidbox.busbymoviesv2.move_list.di

import me.androidbox.busbymoviesv2.configuration.domain.usecases.ConfigurationUseCase
import me.androidbox.busbymoviesv2.move_list.data.repository.MovieListRepository
import me.androidbox.busbymoviesv2.move_list.domain.usecases.MovieListUseCase
import me.androidbox.busbymoviesv2.move_list.domain.usecases.imp.MovieListUseCaseImp
import me.androidbox.busbymoviesv2.move_list.presentation.MoveListViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieListModule = module {
    factory<MovieListUseCase> {
        MovieListUseCaseImp(get<MovieListRepository>())
    }

    // Alternative approach
    // factoryOf(::MovieListNowPlayingUseCaseImp).bind(MovieListNowPlayingUseCase::class)

    viewModel {
        MoveListViewModel(get<MovieListUseCase>(), get<ConfigurationUseCase>())
    }
}