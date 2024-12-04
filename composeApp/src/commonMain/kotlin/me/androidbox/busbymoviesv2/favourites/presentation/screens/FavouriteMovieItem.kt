package me.androidbox.busbymoviesv2.favourites.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.person
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

@Composable
fun FavouriteMovieItem(
    favouriteMovieModel: MovieFavouriteModel,
    modifier: Modifier = Modifier,
    onMovieClicked: (movieId: Int) -> Unit
) {

    val roundedVoteAverage = remember(favouriteMovieModel.voteAverage) {
        (favouriteMovieModel.voteAverage * 10).roundToInt() / 10.0
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(6.dp)
            .clickable {
                onMovieClicked(favouriteMovieModel.id)
            }
    ) {
             KamelImage(
                resource = { asyncPainterResource(data = favouriteMovieModel.imageUrl) },
                contentDescription = favouriteMovieModel.title,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                contentAlignment = Alignment.Center,
                onLoading = { _ ->
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Blue
                    )
                },
                onFailure = {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(Res.drawable.person),
                        contentDescription = "Fall back image"
                    )
                    it.printStackTrace()
                },
            )

            Spacer(modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(3f).fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = favouriteMovieModel.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = favouriteMovieModel.tagline,
                    fontStyle = FontStyle.Italic,
                    color = Color.LightGray,
                    modifier = Modifier.fillMaxWidth())

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Release date ${favouriteMovieModel.releaseDate}",
                    modifier = Modifier.fillMaxWidth())

                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Average votes $roundedVoteAverage",
                    modifier = Modifier.fillMaxWidth())
            }
    }
}