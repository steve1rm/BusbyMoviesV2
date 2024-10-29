package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailOverview(
    movieDetail: MovieDetail,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            StarRatingBar(movieDetail.voteAverage)
        }

        Box(modifier = Modifier.wrapContentWidth()) {
            CircularProgressBar(
                percentage = 0.8f,
                number = 100
            )

          /*  KamelImage(
                resource = { asyncPainterResource(data = movieDetail.posterPath) },
                contentDescription = "",
                modifier = Modifier.size(150.dp, 250.dp),
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
            )*/

        }
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 20.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animationDuration: Int = 1000,
    animationDelay: Int = 0) {

    var hasAnimationPlayed by remember { mutableStateOf(false) }
    val currentPercentage by animateFloatAsState(
        targetValue = if(hasAnimationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    LaunchedEffect(true) {
        hasAnimationPlayed = true
    }

    Box(
        modifier = Modifier.size(radius),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier.size(radius)) {

            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round)
            )
        }

        Text(text = (currentPercentage * number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.SemiBold)
    }

}


@Preview
@Composable
fun PreviewMovideDetailOverview() {
    MaterialTheme {
        MovieDetailOverview(
            movieDetail = MovieDetail()
        )
    }
}