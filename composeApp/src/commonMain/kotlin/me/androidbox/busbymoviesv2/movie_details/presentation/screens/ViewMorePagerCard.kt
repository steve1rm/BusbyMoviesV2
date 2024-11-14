package me.androidbox.busbymoviesv2.movie_details.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ViewMorePagerCard(
    modifier: Modifier = Modifier,
    onViewMoreClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.LightGray)
            .aspectRatio(16f / 9f),
        contentAlignment = Alignment.Center
    ) {

        TextButton(
            onClick = {
                onViewMoreClicked()
            }
        ) {
            Text(text = "View More ->")
        }
    }
}

@Preview
@Composable
fun ViewMorePagerCardPreview() {
    MoviePagerCard(
        imageUrl = "",
        title = "Movie Title Movie Title Movie Title Movie Title Movie Title Movie Title",
        releaseDate = "2023-10-27",
        rating = 0.81f
    )
}