package me.androidbox.busbymoviesv2.move_list.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import me.androidbox.busbymoviesv2.move_list.presentation.MovieCategories
import org.jetbrains.compose.resources.StringResource


data class BottomMovieListNavigationItem(
    val title: StringResource,
    val movieCategory: MovieCategories,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasExtra: Boolean,
    val badgeCount: Int? = null
)

val listOfBottomMovieListNavigationItems = listOf(
    BottomMovieListNavigationItem(
        title = MovieCategories.NOW_PLAYING.titleRes,
        movieCategory = MovieCategories.NOW_PLAYING,
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
        hasExtra = false
    ),
    BottomMovieListNavigationItem(
        title = MovieCategories.TOP_RATED.titleRes,
        movieCategory = MovieCategories.TOP_RATED,
        selectedIcon = Icons.Filled.Favorite,
        unSelectedIcon = Icons.Outlined.Favorite,
        hasExtra = false,
        badgeCount = 5
    ),
    BottomMovieListNavigationItem(
        title = MovieCategories.POPULAR.titleRes,
        movieCategory = MovieCategories.POPULAR,
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person,
        hasExtra = false
    ),
    BottomMovieListNavigationItem(
        title = MovieCategories.UPCOMING.titleRes,
        movieCategory = MovieCategories.UPCOMING,
        selectedIcon = Icons.Filled.Settings,
        unSelectedIcon = Icons.Outlined.Settings,
        hasExtra = true
    )
)
