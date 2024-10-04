package me.androidbox.busbymoviesv2.move_list.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.androidbox.busbymoviesv2.move_list.presentation.MovieListItem
import me.androidbox.busbymoviesv2.move_list.presentation.MovieListState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieListScreen(
    movieListState: MovieListState,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        if(movieListState.isLoading) {
            CircularProgressIndicator()
        }
        else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                modifier = Modifier.fillMaxSize(),
                state = rememberLazyGridState()
            ) {
                items(
                    items = movieListState.movieList,
                    key = {
                        it.id
                    }) {
                        movieResult ->
                    MovieListItem(movieResult)
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewMovieListScreen() {
    MaterialTheme {
        MovieListScreen(
            movieListState = MovieListState()
        )
    }
}
