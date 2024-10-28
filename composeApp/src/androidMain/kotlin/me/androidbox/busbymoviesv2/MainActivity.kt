package me.androidbox.busbymoviesv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import me.androidbox.busbymoviesv2.move_list.presentation.models.MovieResult
import me.androidbox.busbymoviesv2.movie_details.presentation.screens.components.MovieDetailHeader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieDetailHeader(MovieResult())
           // App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}