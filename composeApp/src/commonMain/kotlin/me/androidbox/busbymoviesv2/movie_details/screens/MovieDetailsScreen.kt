package me.androidbox.busbymoviesv2.movie_details.screens

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailsScreen() {
    Text(text = "Movie Details Screen")
}

@Composable
@Preview
fun PreviewMovieDetailsScreen() {
    MaterialTheme {
        MovieDetailsScreen()
    }
}