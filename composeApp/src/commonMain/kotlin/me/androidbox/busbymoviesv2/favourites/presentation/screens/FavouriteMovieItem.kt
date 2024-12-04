package me.androidbox.busbymoviesv2.favourites.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.person
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel
import org.jetbrains.compose.resources.painterResource

@Composable
fun FavouriteMovieItem(
    favouriteMovieModel: MovieFavouriteModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
             KamelImage(
                resource = { asyncPainterResource(data = favouriteMovieModel.imageUrl) },
                contentDescription = favouriteMovieModel.title,
                modifier = Modifier
                    .size(width = 30.dp, height = 90.dp)
                    .aspectRatio(9f / 16f)
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
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = favouriteMovieModel.title)

                Text(text = favouriteMovieModel.tagline)

                Text(text = favouriteMovieModel.releaseDate)
            }

    }
}