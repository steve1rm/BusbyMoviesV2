@file:OptIn(KoinExperimentalAPI::class)

package me.androidbox.busbymoviesv2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailAction
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailViewModel
import me.androidbox.busbymoviesv2.movie_details.presentation.screens.MovieDetailsScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
data class MovieDetailsScreenRoute(private val movieId: Int) : Screen {

    @Composable
    override fun Content() {
        /** We need the movieId to be injected into the viewmodel when loading the details onStart */
        val movieDetailViewModel: MovieDetailViewModel = koinViewModel(parameters = { parametersOf(movieId) })
        val movieDetailState by movieDetailViewModel.movieDetailState.collectAsStateWithLifecycle()
        val urlHandler = LocalUriHandler.current

        MovieDetailsScreen(
            movieDetailState = movieDetailState,
            movieDetailAction = { action ->
                when(action) {
                    MovieDetailAction.OnMovieActorClicked -> TODO()
                    is MovieDetailAction.OnHomePageClicked -> {
                        urlHandler.openUri(action.url)
                    }
                    MovieDetailAction.OnReviewClicked -> TODO()
                    else -> {
                        /** Handle these in the viewmodel as they require any navigation */
                        movieDetailViewModel.onMovieDetailAction(action)
                    }
                }
            }
        )
    }
}