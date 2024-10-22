@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package me.androidbox.busbymoviesv2.move_list.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.LazyPagingItems
import me.androidbox.busbymoviesv2.move_list.presentation.MovieListAction
import me.androidbox.busbymoviesv2.move_list.presentation.MovieListState
import me.androidbox.busbymoviesv2.move_list.presentation.components.NavigationBottomBar
import me.androidbox.busbymoviesv2.move_list.presentation.components.NavigationRailSideBar
import me.androidbox.busbymoviesv2.move_list.presentation.models.MovieResult
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MovieListScreen(
    movieListState: MovieListState,
    onMovieListAction: (MovieListAction) -> Unit,
    movieListPager: LazyPagingItems<MovieResult>,
    modifier: Modifier = Modifier
) {
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    /** TODO Remove this calculate window class as its part of the latest version of compose
     * https://github.com/steve1rm/BusbyMoviesV2/issues/30
     * */
    val windowClass = calculateWindowSizeClass()
    val showNavigationRail = windowClass.widthSizeClass != WindowWidthSizeClass.Compact

    Surface {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(resource = listOfNavigationItems[selectedItemIndex].title))
                    }
                )
            },
            content = { paddingValues ->
                Box(modifier = modifier.fillMaxSize().padding(paddingValues),
                    contentAlignment = Alignment.Center) {

                    if(movieListState.isLoading) {
                        CircularProgressIndicator()
                    }
                    else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(count = 2),
                            modifier = Modifier.fillMaxSize().padding(start = if(showNavigationRail) 68.dp else 0.dp),
                            state = rememberLazyGridState(),
                            content = {
                                items(
                                    count = movieListPager.itemCount,
                                    key = { index ->
                                        index
                                    },
                                    itemContent = { index ->
                                        movieListPager[index]?.let { movieResult ->
                                            MovieListItem(movieResult)
                                        }
                                    }
                                )

                                /**
                                 * Check loading states */
                                when {
                                    movieListPager.loadState.refresh is LoadStateNotLoading && movieListPager.itemCount < 0 -> {
                                        println("REFRESH NO ITEMS")
                                    }

                                    movieListPager.loadState.refresh is LoadStateLoading -> {
                                        item(
                                            span = {
                                                /** Maybe have to change this span when working with landscape or tablet if we have more grid items */
                                                GridItemSpan(currentLineSpan = 2)
                                            }
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 8.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator(
                                                    color = Color.Green
                                                )
                                            }
                                        }
                                    }

                                    movieListPager.loadState.append is LoadStateLoading -> {
                                        item(
                                            span = {
                                                GridItemSpan(currentLineSpan = 2)
                                            }
                                        ) {
                                            Box(
                                                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.align(Alignment.Center),
                                                    color = Color.Green
                                                )
                                            }
                                        }
                                    }

                                    movieListPager.loadState.refresh is LoadStateError -> {
                                        item(
                                            span = {
                                                GridItemSpan(currentLineSpan = 2)
                                            }
                                        ) {
                                            Column(
                                                modifier = modifier.padding(16.dp),
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Text(
                                                    text = "No Internet Connection",
                                                    maxLines = 1,
                                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                                    color = Color.Red
                                                )
                                                OutlinedButton(
                                                    onClick = {},
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(16.dp)
                                                        .wrapContentWidth(Alignment.CenterHorizontally)
                                                ) {
                                                    Text(text = "Try again")
                                                }
                                            }
                                        }
                                    }

                                    movieListPager.loadState.append is LoadStateError -> {
                                        item(
                                            span = {
                                                GridItemSpan(currentLineSpan = 2)
                                            }
                                        ) {
                                            Row(
                                                modifier = modifier.padding(16.dp),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = "No internet connection",
                                                    maxLines = 1,
                                                    modifier = Modifier.weight(1f),
                                                    color = Color.Red
                                                )
                                                OutlinedButton(onClick = {}) {
                                                    Text(text = "Try again")
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            },
            bottomBar = {
                if(!showNavigationRail) {
                    BottomNavigation(
                        elevation = BottomNavigationDefaults.Elevation
                    ) {
                        NavigationBottomBar(
                            listOfNavigationItems,
                            selectedItemIndex = selectedItemIndex,
                            onItemClicked = { movieCategory, index ->
                                onMovieListAction(MovieListAction.OnMovieListNavigationItemClicked(movieCategory))
                                selectedItemIndex = index
                            }
                        )
                    }
                }
            }
        )

        if(showNavigationRail) {
            NavigationRailSideBar(
                listOfNavigationItems = listOfNavigationItems,
                selectedItemIndex = selectedItemIndex,
                onItemClicked = { movieCategory, index ->
                    onMovieListAction(MovieListAction.OnMovieListNavigationItemClicked(movieCategory))
                    selectedItemIndex = index
                }
            )
        }
    }
}

@Composable
@Preview
fun PreviewMovieListScreen() {
    MaterialTheme {
        /* MovieListScreen(
             movieListState = MovieListState(),
             onMovieListAction = {},

         )*/
    }
}
