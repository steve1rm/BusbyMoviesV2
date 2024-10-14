package me.androidbox.busbymoviesv2.move_list.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.compose_multiplatform
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import me.androidbox.busbymoviesv2.core.presentation.utils.getRandomColor
import me.androidbox.busbymoviesv2.move_list.presentation.models.MovieResult
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieListItem(
    movie: MovieResult,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        KamelImage(
            resource = { asyncPainterResource(data = movie.posterPath) },
            contentDescription = "",
            modifier = Modifier.aspectRatio(2f / 3f),
            contentScale = ContentScale.FillHeight,
            onLoading = {_ ->
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Blue
                )
            },
            onFailure = {
                Image(imageVector = vectorResource(Res.drawable.compose_multiplatform), contentDescription = null)
            },
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = getRandomColor().copy(alpha = 0.4f))
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(horizontal = 16.dp),
            maxLines = 2,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            text = movie.title,
            color = Color.Black)
    }
}

@Preview
@Composable
fun PreviewMovieListItem() {
    MaterialTheme {
        MovieListItem(
            MovieResult(
                adult = false,
                backdropPath = "/qB6b593Q958q4n4g5O79j9q9gB.jpg",
                id = 429617,
                originalLanguage = "en",
                originalTitle = "The Shawshank Redemption",
                overview = "Two imprisoned men bond over a decade-long marriage, battling the corruption that plagues their families",
                popularity = 9.27,
                posterPath = "/9O75xZj9Y5p0f868a34x9PegEm1.jpg",
                releaseDate = "1994-09-10",
                title = "The Shawshank Redemption",
                video = false,
                voteAverage = 9.3,
                voteCount = 67876,
                genreIds = emptyList(),
            )
        )
    }
}