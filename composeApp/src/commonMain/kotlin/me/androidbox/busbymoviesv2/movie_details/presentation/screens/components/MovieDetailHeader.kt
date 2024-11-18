package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import chaintech.videoplayer.model.PlayerConfig
import chaintech.videoplayer.ui.video.VideoPlayerView
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailHeader(
    movieDetail: MovieDetail
) {
    val hazeState = remember { HazeState() }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {

        VideoPlayerView(
            modifier = Modifier.aspectRatio(16f / 9f).haze(state = hazeState),
            url = "https://www.youtube.com/watch?v=F7QZTj0k2Ng",
            playerConfig = PlayerConfig(
                isDurationVisible = false,
                isSeekBarVisible = false,
                isScreenResizeEnabled = false
            )
        )
       /* KamelImage(
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
        )*/

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
            )
        )
    }
}

@Preview
@Composable
fun MovieDetailHeaderPreview() {
    MovieDetailHeader(movieDetail = MovieDetail())
}