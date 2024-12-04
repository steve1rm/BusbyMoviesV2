package me.androidbox.busbymoviesv2.move_list.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import me.androidbox.busbymoviesv2.move_list.presentation.MovieCategories
import me.androidbox.busbymoviesv2.move_list.presentation.screens.MovieListNavigationItem
import org.jetbrains.compose.resources.stringResource

@Composable
fun RowScope.NavigationBottomBar(
    listOfNavigationItems: List<MovieListNavigationItem>,
    selectedItemIndex: Int,
    favouriteMovieCount: Int,
    onItemClicked: (movieCategory: MovieCategories, index: Int) -> Unit) {

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
                    onItemClicked(
                        listOfNavigationItems[index].movieCategory,
                        index
                    )
                }
            },
            icon = {
                BadgedBox(
                    badge = {
                        if (navigationItem.badgeCount != null) {
                            Badge {
                                Text(text = favouriteMovieCount.toString())
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
