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
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import me.androidbox.busbymoviesv2.VideoPlayer
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailHeader(
    movieDetail: MovieDetail,
    isFavourite: Boolean,
    onFavouriteClicked: () -> Unit
) {
    val hazeState = remember { HazeState() }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        /** If no videos are available display the image instead */
        if(movieDetail.videos.results.isNotEmpty()) {
            VideoPlayer(
                Modifier.aspectRatio(16f / 9f).haze(state = hazeState),
                url = movieDetail.videos.results.first().key
            )
        }
        else {
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
        }

        MovieTitleHeader(
            title = movieDetail.title,
            tagline = movieDetail.tagline,
            releaseDate = if(movieDetail.releaseDate.isNotBlank()) {
                LocalDate.parse(input = movieDetail.releaseDate)
                    .format(
                        format = LocalDate.Format {
                            monthNumber(padding = Padding.NONE)
                            char(' ')
                            monthName(names = MonthNames.ENGLISH_ABBREVIATED)
                            char(' ')
                            year()
                        }
                    )
            } else { ""},
            modifier = Modifier.align(Alignment.BottomCenter).hazeChild(
                state = hazeState,
                shape = RoundedCornerShape(topStart = 60f, topEnd = 60f)
            ),
            onFavouriteClicked = onFavouriteClicked,
            isFavourite = isFavourite
        )
    }
}

@Preview
@Composable
fun MovieDetailHeaderPreview() {
    MovieDetailHeader(movieDetail = MovieDetail(), onFavouriteClicked = {}, isFavourite = true)
}