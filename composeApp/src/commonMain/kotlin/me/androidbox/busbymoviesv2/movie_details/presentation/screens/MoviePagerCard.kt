package me.androidbox.busbymoviesv2.movie_details.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.dissatisfied
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import me.androidbox.busbymoviesv2.core.presentation.components.CircularProgressBar
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoviePagerCard(
    movieId: Int,
    imageUrl: String,
    title: String,
    releaseDate: String,
    rating: Float,
    modifier: Modifier = Modifier,
    onMovieClicked: (movieId: Int) -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                onClick = {
                    onMovieClicked(movieId)
                }
            )
    ) {

        KamelImage(
            modifier = Modifier
                .aspectRatio(16f / 9f),
            resource = { asyncPainterResource(data = imageUrl)},
            contentDescription = title,
            onLoading = {
                CircularProgressIndicator()
            },
            onFailure = {
                Box(
                    modifier = Modifier.aspectRatio(16f / 9f).background(color = Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(80.dp),
                        imageVector = vectorResource(resource = Res.drawable.dissatisfied),
                        contentDescription = null
                    )
                }
            }
        )

        CircularProgressBar(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(top = 8.dp, end = 16.dp),
            percentage = rating
        )

        Row(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth()
                .height(40.dp)
                .background(color = Color.White.copy(alpha = 0.8f), shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.DateRange,
                contentDescription = null)

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = if(releaseDate.isNotBlank()) {
                    LocalDate.parse(
                        input = releaseDate).format(
                        format = LocalDate.Format {
                            dayOfMonth(padding = Padding.NONE)
                            char(' ')
                            monthName(names = MonthNames.ENGLISH_ABBREVIATED)
                            char(' ')
                            year()
                        }
                    ) } else { ""},
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier.weight(1f),
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.End,
                text = title)
        }
    }
}

@Preview
@Composable
fun MoviePagerCardPreview() {
    MoviePagerCard(
        movieId = 102020,
        imageUrl = "",
        title = "Movie Title Movie Title Movie Title Movie Title Movie Title Movie Title",
        releaseDate = "2023-10-27",
        rating = 0.81f,
        onMovieClicked = {}
    )
}