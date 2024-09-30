package me.androidbox.busbymoviesv2.move_list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.androidbox.busbymoviesv2.move_list.presentation.models.MovieItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieListItem(
    movieItem: MovieItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {

      /* KamelImage(
            resource = asyncPainterResource(data = Url(movieItem.imagePathURI)),
            contentDescription = movieItem.movieName,
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(200.dp)
        )*/

        Text(
            text = movieItem.movieName
        )
    }
}

@Preview
@Composable
fun PreviewMovieListItem() {
    MaterialTheme {
        MovieListItem(
            MovieItem("this is the path", "Alien Romulas")
        )
    }
}