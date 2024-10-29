package me.androidbox.busbymoviesv2.movie_details.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize(),
            ) {
                if(movieDetailState.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            color = Color.Blue
                        )
                    }
                }
                else {
                    MovieDetailHeader(
                        movieDetail = movieDetailState.movieDetail
                    )
                }
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