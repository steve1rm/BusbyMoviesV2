package me.androidbox.busbymoviesv2.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.busbymoviesv2.move_list.presentation.MoveListViewModel
import me.androidbox.busbymoviesv2.move_list.presentation.screens.MovieListScreen
import org.koin.compose.viewmodel.koinViewModel

data object MovieListScreenRoute : Screen {

    @Composable
    override fun Content() {
        val movieListViewModel : MoveListViewModel = koinViewModel()
        val movieListState = movieListViewModel.movieListState

        MovieListScreen(
            movieListState = movieListState,
            onMovieListAction = movieListViewModel::onLoginAction
        )
    }
}