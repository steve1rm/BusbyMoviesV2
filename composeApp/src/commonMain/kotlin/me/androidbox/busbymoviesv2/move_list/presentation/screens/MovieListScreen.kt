package me.androidbox.busbymoviesv2.move_list.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
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
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                items(
                    movieListState.movieList,
                    key = { movieResult ->
                        movieResult.id
                    }
                ) { movieResult ->
                    Column {
                        KamelImage({ asyncPainterResource(data = "https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg") },
                            contentDescription = "",
                            modifier = Modifier.width(100.dp).height(200.dp),
                            contentScale = ContentScale.Crop,
                          )

                         Text(
                             modifier = Modifier.padding(16.dp),
                             text = movieResult.title, color = Color.Black)
                    }
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
