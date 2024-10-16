package me.androidbox.busbymoviesv2.move_list.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import me.androidbox.busbymoviesv2.move_list.presentation.MovieCategories
import org.jetbrains.compose.resources.StringResource


data class MovieListNavigationItem(
    val title: StringResource,
    val movieCategory: MovieCategories,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val hasExtra: Boolean,
    val badgeCount: Int? = null
)

val listOfNavigationItems = listOf(
    MovieListNavigationItem(
        title = MovieCategories.NOW_PLAYING.titleRes,
        movieCategory = MovieCategories.NOW_PLAYING,
        selectedIcon = Icons.Filled.PlayArrow,
        unSelectedIcon = Icons.Outlined.PlayArrow,
        hasExtra = false
    ),
    MovieListNavigationItem(
        title = MovieCategories.TOP_RATED.titleRes,
        movieCategory = MovieCategories.TOP_RATED,
        selectedIcon = Icons.Filled.Star,
        unSelectedIcon = Icons.Outlined.Star,
        hasExtra = false,
        badgeCount = 5
    ),
    MovieListNavigationItem(
        title = MovieCategories.POPULAR.titleRes,
        movieCategory = MovieCategories.POPULAR,
        selectedIcon = Icons.Filled.ThumbUp,
        unSelectedIcon = Icons.Outlined.ThumbUp,
        hasExtra = false
    ),
    MovieListNavigationItem(
        title = MovieCategories.UPCOMING.titleRes,
        movieCategory = MovieCategories.UPCOMING,
        selectedIcon = Icons.Filled.DateRange,
        unSelectedIcon = Icons.Outlined.DateRange,
        hasExtra = true
    )
)
