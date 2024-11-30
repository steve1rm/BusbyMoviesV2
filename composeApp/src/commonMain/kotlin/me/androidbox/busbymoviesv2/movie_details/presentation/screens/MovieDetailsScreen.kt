@file:OptIn(ExperimentalMaterialApi::class)

package me.androidbox.busbymoviesv2.movie_details.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import me.androidbox.busbymoviesv2.VideoPlayer
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailAction
import me.androidbox.busbymoviesv2.movie_details.presentation.MovieDetailState
import me.androidbox.busbymoviesv2.movie_details.presentation.screens.components.MovieDetailHeader
import me.androidbox.busbymoviesv2.movie_details.presentation.screens.components.MovieDetailOverview
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieDetailsScreen(
    movieDetailState: MovieDetailState,
    movieDetailAction: (movieDetailAction: MovieDetailAction) -> Unit
) {

    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Collapsed,
            confirmStateChange = { bottomSheetValue ->
                when(bottomSheetValue) {
                    BottomSheetValue.Collapsed -> {
                        println("Collapsed")
                    }
                    BottomSheetValue.Expanded -> {
                        println("Expanded")
                    }
                }

                true
            }
        )
    )

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = {
            movieDetailState.movieDetail.videos.results.count()
        }
    )

    @OptIn(ExperimentalMaterialApi::class)
    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetPeekHeight = 0.dp,
        scaffoldState = bottomSheetState,
        sheetContent = {
            Spacer(modifier = Modifier.height(16.dp))

            HorizontalPager(
                state = pagerState,
                pageContent = { page ->
                    Column(
                        modifier = Modifier.fillMaxWidth()) {

                        Text(text = movieDetailState.movieDetail.videos.results[page].name,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis)

                        VideoPlayer(
                            Modifier.aspectRatio(16f / 9f),
                            url = movieDetailState.movieDetail.videos.results[page].key
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize(),
            ) {
                if(movieDetailState.isLoadingDetails) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            color = Color.Blue)
                    }
                }
                else {
                    remember(1f) {
                        ColorMatrix().apply {
                            this.setToSaturation(1f)
                        }
                    }

                    ColorFilter.lighting(
                        add = Color.White.copy(alpha = 0.2f), // Adjust alpha for lightening intensity
                        multiply = Color.White
                    )
                    Column(modifier = Modifier.fillMaxSize()) {
                        MovieDetailHeader(
                            movieDetail = movieDetailState.movieDetail
                        )

                        Box(modifier = Modifier.fillMaxSize()) {
                            KamelImage(
                                resource = { asyncPainterResource(data = movieDetailState.movieDetail.backdropPath) },
                                contentDescription = movieDetailState.movieDetail.title,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop,
                                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                                alpha = 0.2f,
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
                            Column(modifier = Modifier.fillMaxSize()) {
                                Spacer(modifier = Modifier.height(16.dp))
                                MovieDetailOverview(
                                    movieDetailState = movieDetailState,
                                    onMovieClicked = { movieId ->
                                       movieDetailAction(MovieDetailAction.OnSimilarMovieClicked(movieId))
                                       },
                                    onHomePageClicked = { url ->
                                       movieDetailAction(MovieDetailAction.OnHomePageClicked(url))
                                    },
                                    onTrailerClicked = {
                                        coroutineScope.launch {
                                            if(bottomSheetState.bottomSheetState.isCollapsed) {
                                                bottomSheetState.bottomSheetState.expand()
                                            }
                                            else {
                                                bottomSheetState.bottomSheetState.collapse()
                                            }
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        })
}

@Composable
@Preview
fun PreviewMovieDetailsScreen() {
    MaterialTheme {
        MovieDetailsScreen(
            movieDetailState = MovieDetailState(),
            movieDetailAction = {}
        )
    }
}