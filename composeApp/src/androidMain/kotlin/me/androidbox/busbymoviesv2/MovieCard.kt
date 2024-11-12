package me.androidbox.busbymoviesv2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.person
import coil3.compose.SubcomposeAsyncImage
import me.androidbox.busbymoviesv2.core.presentation.components.CircularProgressBar
import org.jetbrains.compose.resources.painterResource

@Composable
fun MoviePagerCard(
    imageUrl: String,
    title: String,
    releaseDate: String,
    rating: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
    ) {

        SubcomposeAsyncImage(
            modifier = Modifier.aspectRatio(16f/9f),
            model =  imageUrl, //"https://image.tmdb.org/t/p/w500/8mjYwWT50GkRrrRdyHzJorfEfcl.jpg",
            contentDescription = title,
            loading = {
                CircularProgressIndicator()
            },
            error = {
                Image(
                    painter = painterResource(resource = Res.drawable.person),
                    contentDescription = null
                )
            }
        )

        CircularProgressBar(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp),
            percentage = rating
        )

        Row(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.Black.copy(alpha = 0.5f))
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Default.DateRange,
                contentDescription = null)

            Spacer(modifier = Modifier.width(4.dp))

            Text(text = releaseDate, color = Color.Black)

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                modifier = Modifier.weight(1f),
                color = Color.Black,
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
        imageUrl = "",
        title = "Movie Title Movie Title Movie Title Movie Title Movie Title Movie Title",
        releaseDate = "2023-10-27",
        rating = 0.81f
    )
}