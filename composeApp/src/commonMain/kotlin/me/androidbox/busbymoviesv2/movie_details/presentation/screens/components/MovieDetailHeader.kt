@file:OptIn(ExperimentalHazeMaterialsApi::class)

package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailHeader(
    movieDetail: MovieDetail
) {
    val hazeState = remember { HazeState() }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        KamelImage(
            resource = { asyncPainterResource(data = movieDetail.backdropPath) },
            contentDescription = movieDetail.title,
            modifier = Modifier.aspectRatio(16f / 9f).haze(state = hazeState),
            contentScale = ContentScale.FillHeight,
            onLoading = {_ ->
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Blue
                )
            },
            onFailure = {
                it.printStackTrace()
            },
        )

        MovieTitleHeader(
            title = movieDetail.title,
            tagline = movieDetail.tagline,
            releaseDate = movieDetail.releaseDate,
            modifier = Modifier.align(Alignment.BottomCenter).hazeChild(
                state = hazeState,
                shape = RoundedCornerShape(topStart = 60f, topEnd = 60f)
            )
        )
    }
}

@Preview
@Composable
fun MovieDetailHeaderPreview() {
    MovieDetailHeader(movieDetail = MovieDetail())
}