package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.feedback
import busbymoviesv2.composeapp.generated.resources.movie
import me.androidbox.busbymoviesv2.core.presentation.components.MovieButton
import me.androidbox.busbymoviesv2.core.presentation.components.StarRatingBar
import me.androidbox.busbymoviesv2.core.presentation.utils.formatNumberWithSuffix
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Cast
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Credits
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail
import me.androidbox.busbymoviesv2.movie_details.presentation.screens.MovieCastList
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Duration.Companion.minutes

@Composable
fun MovieDetailOverview(
    movieDetail: MovieDetail,
    credits: Credits,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
    ) {

        Row(
            modifier = modifier
                .fillMaxHeight()
                .height(intrinsicSize = IntrinsicSize.Max)
                .padding(horizontal = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight().weight(1f).padding(end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier.wrapContentHeight()
                ) {
                    StarRatingBar(movieDetail.voteAverage)

                    Text(
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        text = movieDetail.genres.joinToString(" • ") { genre -> genre.name },
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )

                    Row {
                        Text(
                            modifier = Modifier.weight(1f),
                            color = Color.Black,
                            text = "Running time:"
                        )

                        Text(
                            modifier = Modifier.wrapContentWidth(),
                            color = Color.Black,
                            text = "${movieDetail.runtime.minutes}"
                        )

                    }

                    /** Don't show if there is zero budget or revenue */
                    if (movieDetail.revenue > 0) {
                        Row {
                            Text(
                                modifier = Modifier.weight(1f),
                                color = Color.Black,
                                text = "Revenue:"
                            )

                            Text(
                                modifier = Modifier.wrapContentWidth(),
                                color = Color.Black,
                                text = "$${movieDetail.revenue.formatNumberWithSuffix()}"
                            )
                        }

                    }

                    if (movieDetail.budget > 0) {
                        Row {
                            Text(
                                modifier = Modifier.weight(1f),
                                color = Color.Black,
                                text = "Budget:"
                            )

                            Text(
                                modifier = Modifier.wrapContentWidth(),
                                color = Color.Black,
                                text = "$${movieDetail.budget.formatNumberWithSuffix()}"
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .offset(y = -(32.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                   MovieButton(
                        modifier = Modifier.alpha(0.6f).weight(1f),
                        iconRes = Res.drawable.feedback,
                        text = "6 Reviews",
                        onClicked = {}
                    )

                    MovieButton(
                        modifier = Modifier.alpha(0.6f).weight(1f),
                        iconRes = Res.drawable.movie,
                        text = "10 Trailers",
                        onClicked = {}
                    )
                }
            }

            MovieRating(
                movieDetail = movieDetail)
        }

        Column(modifier = Modifier
            .fillMaxSize().offset(y = -(24.dp)).zIndex(-1f).alpha(0.8f)
            .background(color = Color.White, shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp))) {
            Text(
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                text = "Overview"
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                text = movieDetail.overview
            )

            /** Top billed cast */
            Spacer(modifier = Modifier.height(8.dp))
            MovieCastList(credits)
        }
    }
}

fun listOfCast(): List<Cast> {
    return listOf(
        Cast(
            id = 1,
            name = "John Doe",
            character = "Character",
            popularity = 10.0,
            creditId = "001",
            castId = 130,
            profilePath = "https://image.tmdb.org/t/p/w500/kU3B75TyRiCgE270EyZnHjfivoq.jpg"
        ))
}

@Preview
@Composable
fun PreviewMovieDetailOverview() {
    MaterialTheme {
        MovieDetailOverview(
            movieDetail = MovieDetail(),
            credits = Credits()
        )
    }
}
