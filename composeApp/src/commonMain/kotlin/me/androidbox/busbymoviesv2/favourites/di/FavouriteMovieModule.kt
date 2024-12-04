package me.androidbox.busbymoviesv2.favourites.di

import me.androidbox.busbymoviesv2.favourites.presentation.FavouriteMovieViewModel
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.GetFavouriteMoviesUseCase
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteMovieModule = module {

    viewModel {
        FavouriteMovieViewModel(get<GetFavouriteMoviesUseCase>())
    }
}