package me.androidbox.busbymoviesv2.actors.di


import androidx.lifecycle.SavedStateHandle
import me.androidbox.busbymoviesv2.actors.presentation.ActorsViewModel
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.MovieCreditsUseCase
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module


val actorModule = module {

    viewModel {
        ActorsViewModel(
            get<MovieCreditsUseCase>(),
            savedStateHandle = SavedStateHandle()
        )
    }
}