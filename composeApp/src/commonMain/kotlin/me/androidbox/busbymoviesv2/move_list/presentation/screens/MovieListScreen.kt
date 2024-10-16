@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package me.androidbox.busbymoviesv2.move_list.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
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
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.LazyPagingItems
import me.androidbox.busbymoviesv2.move_list.presentation.MovieListAction
import me.androidbox.busbymoviesv2.move_list.presentation.MovieListState
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
                                    items = movieListPager.itemSnapshotList.items,
                                    key = { movieResult ->
                                        movieResult.id
                                    },
                                    itemContent = { movieResult ->
                                        MovieListItem(movieResult)
                                    }
                                )
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
                        listOfNavigationItems.forEachIndexed { index, navigationItem ->
                            BottomNavigationItem(
                                label = {
                                    Text(text = stringResource(resource = navigationItem.title))
                                },
                                selected = selectedItemIndex == index,
                                onClick = {
                                    /* Don't trigger a new request if the user is on the same category */
                                    if (selectedItemIndex != index) {
                                        /** Just want to load a different movie list i.e. now playing, trending, popular, upcoming */
                                        onMovieListAction(
                                            MovieListAction.OnMovieListNavigationItemClicked(
                                                listOfNavigationItems[index].movieCategory
                                            )
                                        )
                                    }

                                    selectedItemIndex = index
                                },
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (navigationItem.badgeCount != null) {
                                                Badge {
                                                    Text(text = navigationItem.badgeCount.toString())
                                                }
                                            } else if (navigationItem.hasExtra) {
                                                Badge()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (selectedItemIndex == index)
                                                navigationItem.selectedIcon else navigationItem.unSelectedIcon,
                                            contentDescription = stringResource(resource = navigationItem.title)
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        )

        if(showNavigationRail) {
            NavigationRailSideBar(
                listOfNavigationItems = listOfNavigationItems,
                selectedItemIndex = selectedItemIndex,
                onItemClicked = { movieListAction, index ->
                    println(movieListAction.toString())
                    selectedItemIndex = index

                    onMovieListAction(
                        MovieListAction.OnMovieListNavigationItemClicked(
                            listOfNavigationItems[index].movieCategory
                        )
                    )
                }
            )
        }
    }
}



@Composable
fun NavigationRailSideBar(
    listOfNavigationItems: List<BottomMovieListNavigationItem>,
    selectedItemIndex: Int,
    onItemClicked: (MovieListAction, Int) -> Unit
) {
    NavigationRail(
        modifier = Modifier.padding(top = 56.dp)
    ) {
        listOfNavigationItems.forEachIndexed { index, navigationItem ->
            NavigationRailItem(
                label = {
                    Text(text = stringResource(navigationItem.title))
                },
                selected = selectedItemIndex == index,
                onClick = {
                    /* Don't trigger a new request if the user is on the same category */
                    if (selectedItemIndex != index) {
                        /** Just want to load a different movie list i.e. now playing, trending, popular, upcoming */
                        onItemClicked(
                            MovieListAction.OnMovieListNavigationItemClicked(
                                listOfNavigationItems[index].movieCategory,
                            ),
                            index
                        )
                    }
                },
                icon = {
                    BadgedBox(
                        badge = {
                            if (navigationItem.badgeCount != null) {
                                Badge {
                                    Text(text = navigationItem.badgeCount.toString())
                                }
                            } else if (navigationItem.hasExtra) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (selectedItemIndex == index)
                                navigationItem.selectedIcon else navigationItem.unSelectedIcon,
                            contentDescription = stringResource(resource = navigationItem.title)
                        )
                    }
                })
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
