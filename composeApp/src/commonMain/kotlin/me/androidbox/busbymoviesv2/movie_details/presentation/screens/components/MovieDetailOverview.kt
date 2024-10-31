package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Duration.Companion.minutes

@Composable
fun MovieDetailOverview(
    movieDetail: MovieDetail,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {

                Column(
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
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
                                text = "Revenue:")

                            Text(
                                modifier = Modifier.wrapContentWidth(),
                                color = Color.Black,
                                text = "$${movieDetail.revenue.formatNumberWithSuffix()}"
                            )
                        }

                    }

                    if (movieDetail.budget > 0) {
                        Row{
                            Text(
                                modifier = Modifier.weight(1f),
                                color = Color.Black,
                                text = "Budget:")

                            Text(
                                modifier = Modifier.wrapContentWidth(),
                                color = Color.Black,
                                text = "$${movieDetail.budget.formatNumberWithSuffix()}"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxSize(),
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
        }

        Column(modifier = Modifier
            .fillMaxSize().offset(y = -(16.dp)).zIndex(-1f)
            .background(color = Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                text = "Overview"
            )

            Text(
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                text = movieDetail.overview
            )
        }
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
