package me.androidbox.busbymoviesv2.move_list.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(resource = listOfBottomMovieListNavigationItems[selectedItemIndex].title))
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
                        modifier = Modifier.fillMaxSize(),
                        state = rememberLazyGridState()
                    ) {

                        items(movieListPager.itemCount) { index ->
                            val item = movieListPager[index]

                            item?.let {
                                MovieListItem(it)
                            }
                        }
/*

                        items(
                            items = movieListPager.itemSnapshotList,
                            key = { movieResult ->
                                movieResult?.id ?: Random.nextInt()
                            }) {
                            it?.let {
                                MovieListItem(it)
                            }
                        }
*/

                        /*  items(
                              items = movieListState.movieList,
                              key = {
                                  it.id
                              }) {
                                  movieResult ->
                              MovieListItem(movieResult)
                          }*/
                    }
                }
            }
        },
        bottomBar = {
            BottomNavigation(
                elevation = BottomNavigationDefaults.Elevation
            ) {
                listOfBottomMovieListNavigationItems.forEachIndexed { index, bottomNavigationItem ->
                    BottomNavigationItem(
                        label = {
                            Text(text = stringResource(resource = bottomNavigationItem.title))
                        },
                        selected = selectedItemIndex == index,
                        onClick = {
                            /* Don't trigger a new request if the user is on the same category */
                            if(selectedItemIndex != index) {
                                /** Just want to load a different movie list i.e. now playing, trending, popular, upcoming */
                                onMovieListAction(MovieListAction.OnMovieListNavigationItemClicked(
                                    listOfBottomMovieListNavigationItems[index].movieCategory
                                ))
                            }

                            selectedItemIndex = index
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if(bottomNavigationItem.badgeCount != null) {
                                        Badge {
                                            Text(text = bottomNavigationItem.badgeCount.toString())
                                        }
                                    }
                                    else if(bottomNavigationItem.hasExtra){
                                        Badge()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (selectedItemIndex == index)
                                        bottomNavigationItem.selectedIcon else bottomNavigationItem.unSelectedIcon,
                                    contentDescription = stringResource(resource = bottomNavigationItem.title)
                                )
                            }
                        }
                    )
                }
            }
        }
    )
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
