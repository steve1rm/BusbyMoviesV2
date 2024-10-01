package me.androidbox.busbymoviesv2.move_list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
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

        /*
        KamelImage(
            { asyncPainterResource(data = Url("https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg")) },
            contentDescription = movieItem.movieName,
            modifier = Modifier.fillMaxWidth().height(200.dp),
            contentScale = ContentScale.Crop
        )*/

       /* KamelImage(
            modifier = Modifier.size(40.dp),
            resource = asyncPainterResource(
                data = coinListState.imageUri
            ),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            onLoading = {
                CircularProgressIndicator(
                    color = Color.Blue
                )
            }
        )*/

      /*  Text(
            text = movieItem.movieName
        )*/
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