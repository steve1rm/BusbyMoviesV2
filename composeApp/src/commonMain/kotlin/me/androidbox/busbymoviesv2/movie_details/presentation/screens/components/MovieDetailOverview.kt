package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    StarRatingBar(movieDetail.voteAverage)

                    Text(
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        text = movieDetail.genres.joinToString(" • ") { genre -> genre.name },
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )

                    Text(
                        color = Color.DarkGray,
                        text = "Running time ${movieDetail.runtime.minutes}")
                    Text(text = "Revenue $${movieDetail.revenue.formatNumberWithSuffix()}")
                    /** Don't show if there is zero budget*/
                    if (movieDetail.budget > 0) {
                        Text(text = "Budget $${movieDetail.budget.formatNumberWithSuffix()}")
                    }

                    Text(
                        color = Color.LightGray,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Overview")


                    Text(
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        text = movieDetail.overview
                    )
                }

                MovieRating(movieDetail)
            }

           /* Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MovieButton(
                    iconRes = Res.drawable.feedback,
                    text = "6 Reviews",
                    onClicked = {}
                )

                MovieButton(
                    iconRes = Res.drawable.movie,
                    text = "10 Trailers",
                    onClicked = {}
                )
            }*/

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