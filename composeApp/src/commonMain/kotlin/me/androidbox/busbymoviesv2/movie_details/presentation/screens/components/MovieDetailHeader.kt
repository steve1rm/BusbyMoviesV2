@file:OptIn(ExperimentalHazeMaterialsApi::class)

package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.Image
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
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.compose_multiplatform
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailHeader(
    movieDetail: MovieDetail
) {
    val hazeState = remember { HazeState() }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        // /417tYZ4XUyJrtyZXj7HpvWf1E8f.jpg

        KamelImage(
            resource = { asyncPainterResource(data = "https://image.tmdb.org/t/p/w500/iURqo6oIwbeegIwWXx5qrBsDcPk.jpg") },
            contentDescription = "",
            modifier = Modifier.aspectRatio(16f / 9f).haze(state = hazeState),
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