@file:OptIn(KoinExperimentalAPI::class)

package me.androidbox.busbymoviesv2.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.busbymoviesv2.favourites.presentation.FavouriteMovieViewModel
import me.androidbox.busbymoviesv2.favourites.presentation.screens.FavouriteScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

data object MovieFavouriteScreenRoute : Screen {

    @Composable
    override fun Content() {
        val favouriteMovieViewModel = koinViewModel<FavouriteMovieViewModel>()
        val favouriteMovieState = favouriteMovieViewModel.favouriteMovieState.collectAsStateWithLifecycle()

        FavouriteScreen(
            favouriteMovieState.value.listOfFavouriteMovies
        )
    }
}