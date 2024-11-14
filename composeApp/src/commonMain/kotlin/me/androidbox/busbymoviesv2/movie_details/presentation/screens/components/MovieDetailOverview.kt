@file:OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)

package me.androidbox.busbymoviesv2.movie_details.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
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
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailState
import me.androidbox.busbymoviesv2.movie_details.presentation.screens.CastItem
import me.androidbox.busbymoviesv2.movie_details.presentation.screens.MoviePagerCard
import me.androidbox.busbymoviesv2.movie_details.presentation.screens.ViewMorePagerCard
import me.androidbox.busbymoviesv2.movie_details.presentation.utils.extractDistinctCrewJobs
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.max
import kotlin.time.Duration.Companion.minutes

@Composable
fun MovieDetailOverview(
    movieDetailState: MovieDetailState,
    onMovieClicked: (movieId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var maxWidth by remember {
        mutableIntStateOf(0)
    }

    val maxWidthDp = with(LocalDensity.current) {
        maxWidth.toDp()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {

        Row(
            modifier = modifier
                .fillMaxHeight()
                .height(intrinsicSize = IntrinsicSize.Max)
                .padding(horizontal = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(end = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                ) {
                    StarRatingBar(movieDetailState.movieDetail.voteAverage)

                    Text(
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        text = movieDetailState.movieDetail.genres.joinToString(" • ") { genre -> genre.name },
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
                            text = "${movieDetailState.movieDetail.runtime.minutes}"
                        )

                    }

                    /** Don't show if there is zero budget or revenue */
                    if (movieDetailState.movieDetail.revenue > 0) {
                        Row {
                            Text(
                                modifier = Modifier.weight(1f),
                                color = Color.Black,
                                text = "Revenue:"
                            )

                            Text(
                                modifier = Modifier.wrapContentWidth(),
                                color = Color.Black,
                                text = "$${movieDetailState.movieDetail.revenue.formatNumberWithSuffix()}"
                            )
                        }
                    }

                    if (movieDetailState.movieDetail.budget > 0) {
                        Row {
                            Text(
                                modifier = Modifier.weight(1f),
                                color = Color.Black,
                                text = "Budget:"
                            )

                            Text(
                                modifier = Modifier.wrapContentWidth(),
                                color = Color.Black,
                                text = "$${movieDetailState.movieDetail.budget.formatNumberWithSuffix()}"
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
                movieDetail = movieDetailState.movieDetail)
        }

        /** Overview */
        Column(modifier = Modifier
            .fillMaxSize().offset(y = -(24.dp)).zIndex(-1f).alpha(0.9f)
            .background(color = Color.White, shape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp))) {
            Text(
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                text = "Overview"
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                text = movieDetailState.movieDetail.overview
            )

            /** director and crew details */
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(start = 8.dp, top = 4.dp),
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                text = "Credits"
            )

            Spacer(modifier = Modifier.height(4.dp))

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val crewJobs = extractDistinctCrewJobs(movieDetailState.movieCredits.crew)
                crewJobs.forEach { (name, job) ->
                    CrewItem(
                        modifier = Modifier.onSizeChanged { currentSize ->
                            maxWidth = max(maxWidth, currentSize.width)
                        }.defaultMinSize(minWidth = maxWidthDp),
                        name = name,
                        job = job
                    )
                }
            }

            /** Top billed cast */
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = "Top Billed Cast",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.height(4.dp))

            MovieAnyItemList(
                items = movieDetailState.movieCredits.cast,
                itemContent = { cast ->
                    CastItem(
                        cast = cast,
                        onCastClicked = {
                            println("Cast item clicked $it")
                        }
                    )
                },
                viewMoreContent = {
                    TextButton(
                        onClick = {
                            println("Lets seem some more")
                        }
                    ) {
                        Text(
                            text = "View More ->", fontSize = 18.sp, color = Color.Black,
                            textAlign = TextAlign.Center)
                    }
                },
                isLoading = movieDetailState.isLoadingCredits)

            if(movieDetailState.listOfMovieDetails.isNotEmpty()) {
                /** Similar Movies */
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = "Recommended Movies",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(4.dp))

                if (movieDetailState.listOfMovieDetails.isNotEmpty()) {
                    GenericItemPager(
                        items = movieDetailState.listOfMovieDetails.take(9),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        content = { movieResult ->
                            MoviePagerCard(
                                movieId = movieResult.id,
                                imageUrl = movieResult.backdropPath,
                                title = movieResult.title,
                                releaseDate = movieResult.releaseDate,
                                rating = (movieResult.voteAverage / 10).toFloat(),
                                onMovieClicked = { movieId ->
                                    onMovieClicked(movieId)
                                }
                            )
                        },
                        viewMoreContent = {
                            ViewMorePagerCard {
                                println("View More Clicked")
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewMovieDetailOverview() {
    MaterialTheme {
        MovieDetailOverview(
           movieDetailState = MovieDetailState(),
            onMovieClicked = {}
        )
    }
}
