package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.compose_multiplatform
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import me.androidbox.busbymoviesv2.core.presentation.components.CircularProgressBar
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail
import org.jetbrains.compose.resources.vectorResource

@Composable
fun MovieRating(movieDetail: MovieDetail) {
    Box(modifier = Modifier
        .wrapContentWidth()
        .padding(top = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.Center) {

        KamelImage(
            resource = { asyncPainterResource(data = movieDetail.posterPath) },
            contentDescription = movieDetail.title,
            modifier = Modifier.size(150.dp, 250.dp).clip(RoundedCornerShape(8.dp)),
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

        CircularProgressBar(
            modifier = Modifier.align(Alignment.TopCenter).offset(y = (-16).dp),
            percentage = 0.8f,
            number = 100
        )
    }
}
