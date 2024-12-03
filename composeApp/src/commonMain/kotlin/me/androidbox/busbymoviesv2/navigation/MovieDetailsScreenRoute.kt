@file:OptIn(KoinExperimentalAPI::class)

package me.androidbox.busbymoviesv2.navigation

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.core.presentation.utils.ObserveAsEvents
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailAction
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailEvent
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

        val coroutineScope = rememberCoroutineScope()
        val snackBarHostState = remember {
            SnackbarHostState()
        }

        ObserveAsEvents(
            event = movieDetailViewModel.movieDetailEvent,
            onEvent = { movieDetailEvent ->
                when(movieDetailEvent) {
                    MovieDetailEvent.OnFavouriteSaved -> {
                        coroutineScope.launch {
                            val result = snackBarHostState.showSnackbar(
                                message = "${movieDetailState.movieDetail.title} Saved to favourites",
                                actionLabel = "Undo",
                                duration = SnackbarDuration.Long
                            )

                            when (result) {
                                SnackbarResult.Dismissed -> { /* no-op */ }
                                SnackbarResult.ActionPerformed -> {
                                    println("Undo from saved, remove from db")
                                    movieDetailViewModel.onMovieDetailAction(MovieDetailAction.OnDeleteFavouriteClicked)
                                }
                            }
                        }
                    }
                }
            })

        MovieDetailsScreen(
            movieDetailState = movieDetailState,
            snackBarHostState = snackBarHostState,
            movieDetailAction = { action ->
                when(action) {
                    MovieDetailAction.OnMovieActorClicked -> TODO()
                    is MovieDetailAction.OnHomePageClicked -> {
                        urlHandler.openUri(action.url)
                    }
                    MovieDetailAction.OnReviewClicked -> TODO()
                    else -> {
                        /** Handle these in the viewmodel */
                        movieDetailViewModel.onMovieDetailAction(action)
                    }
                }
            }
        )
    }
}