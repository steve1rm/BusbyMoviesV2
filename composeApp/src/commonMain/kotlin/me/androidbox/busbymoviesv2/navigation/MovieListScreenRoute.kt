@file:OptIn(KoinExperimentalAPI::class)

package me.androidbox.busbymoviesv2.navigation

import androidx.compose.runtime.Composable
import app.cash.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import me.androidbox.busbymoviesv2.move_list.presentation.MoveListViewModel
import me.androidbox.busbymoviesv2.move_list.presentation.screens.MovieListScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


data object MovieListScreenRoute : Screen {

    @Composable
    override fun Content() {
        val movieListViewModel : MoveListViewModel = koinViewModel()
        val movieListState = movieListViewModel.movieListState
        val pager = movieListViewModel.movieListFlow.collectAsLazyPagingItems()


        MovieListScreen(
            movieListState = movieListState,
            onMovieListAction = movieListViewModel::onMovieListAction,
            movieListPager = pager
        )
    }
}