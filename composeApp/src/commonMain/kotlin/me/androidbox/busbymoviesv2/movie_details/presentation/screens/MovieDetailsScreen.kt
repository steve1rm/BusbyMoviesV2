package me.androidbox.busbymoviesv2.movie_details.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailAction
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailState
import me.androidbox.busbymoviesv2.movie_details.presentation.screens.components.MovieDetailHeader
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailsScreen(
    movieDetailState: MovieDetailState,
    movieDetailAction: (movieDetailAction: MovieDetailAction) -> Unit
) {
    Scaffold(
        topBar = {

        },
        content = { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                MovieDetailHeader(
                    movieDetail = movieDetailState.movieDetail
                )
            }
        }
    )
}

@Composable
@Preview
fun PreviewMovieDetailsScreen() {
    MaterialTheme {
        MovieDetailsScreen(
            movieDetailState = MovieDetailState(),
            movieDetailAction = {}
        )
    }
}