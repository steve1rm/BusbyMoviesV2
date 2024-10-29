@file:OptIn(KoinExperimentalAPI::class)

package me.androidbox.busbymoviesv2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailViewModel
import me.androidbox.busbymoviesv2.movie_details.presentation.screens.MovieDetailsScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
data class MovieDetailsScreenRoute(private val movieId: Int) : Screen {

    @Composable
    override fun Content() {
        val movieDetailViewModel: MovieDetailViewModel = koinViewModel(parameters = { parametersOf(movieId) })
        val movieDetailState by movieDetailViewModel.movieDetailState.collectAsStateWithLifecycle()

        MovieDetailsScreen(
            movieDetailState = movieDetailState,
            movieDetailAction = movieDetailViewModel::onMovieDetailAction
        )
    }
}