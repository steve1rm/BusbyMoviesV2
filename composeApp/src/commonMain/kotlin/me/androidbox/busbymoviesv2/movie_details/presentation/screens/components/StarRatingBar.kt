package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.star
import busbymoviesv2.composeapp.generated.resources.star_empty
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt


@Composable
fun StarRatingBar(
    rating: Double,
    modifier: Modifier = Modifier
) {
    val startRating = (rating / 2).roundToInt()

    Row(modifier = modifier.wrapContentWidth()) {
        repeat(5) { index ->
            Icon(
                painter = painterResource(resource = if(index < startRating) Res.drawable.star else Res.drawable.star_empty),
                contentDescription = "Start Rating",
                tint = if(index < startRating) Color.Yellow else Color.LightGray
            )
        }
    }
}

@Composable
@Preview
fun PreviewStarRatingBar() {
    MaterialTheme {
        StarRatingBar(7.3)
    }
}
