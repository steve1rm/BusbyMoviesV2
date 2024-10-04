package me.androidbox.busbymoviesv2

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import me.androidbox.busbymoviesv2.move_list.presentation.MoveListViewModel
import me.androidbox.busbymoviesv2.move_list.presentation.MovieCategories
import me.androidbox.busbymoviesv2.move_list.presentation.MovieListAction
import me.androidbox.busbymoviesv2.move_list.presentation.screens.MovieListScreen
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

data class BottomNavigationItem(
    val title: StringResource,
    val movieCategory: MovieCategories,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasExtra: Boolean,
    val badgeCount: Int? = null
)

val listOfBottomNavigationItem = listOf(
    BottomNavigationItem(
        title = MovieCategories.NOW_PLAYING.titleRes,
        movieCategory = MovieCategories.NOW_PLAYING,
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
        hasExtra = false
    ),
   BottomNavigationItem(
        title = MovieCategories.TOP_RATED.titleRes,
        movieCategory = MovieCategories.TOP_RATED,
        selectedIcon = Icons.Filled.Favorite,
        unSelectedIcon = Icons.Outlined.Favorite,
        hasExtra = false,
        badgeCount = 5
    ),
    BottomNavigationItem(
        title = MovieCategories.POPULAR.titleRes,
        movieCategory = MovieCategories.POPULAR,
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person,
        hasExtra = false
    ),
    BottomNavigationItem(
        title = MovieCategories.UPCOMING.titleRes,
        movieCategory = MovieCategories.UPCOMING,
        selectedIcon = Icons.Filled.Settings,
        unSelectedIcon = Icons.Outlined.Settings,
        hasExtra = true
    )
)

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {

    val movieListViewModel : MoveListViewModel = koinViewModel()
    val movieListState = movieListViewModel.movieListState

    MaterialTheme {
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(resource = listOfBottomNavigationItem[selectedItemIndex].title))
                    }
                )
            },
            content = { paddingValues ->
                MovieListScreen(
                    movieListState = movieListState,
                    modifier = Modifier.padding(paddingValues))
            },
            bottomBar = {
                BottomNavigation(
                    elevation = BottomNavigationDefaults.Elevation
                ) {
                    listOfBottomNavigationItem.forEachIndexed { index, bottomNavigationItem ->
                        BottomNavigationItem(
                            label = {
                                Text(text = stringResource(resource = bottomNavigationItem.title))
                            },
                            selected = selectedItemIndex == index,
                            onClick = {
                                /* Don't trigger a new request if the user is on the same category */
                                if(selectedItemIndex != index) {
                                    /** Just want to load a different movie list i.e. now playing, trending, popular, upcoming */
                                    movieListViewModel.onLoginAction(
                                        MovieListAction.OnMovieListNavigationItemClicked(
                                            listOfBottomNavigationItem[index].movieCategory
                                        )
                                    )
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



/*
       var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }

            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
*/
    }
}