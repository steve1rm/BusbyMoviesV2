package me.androidbox.busbymoviesv2.favourites.presentation.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

@Composable
fun FavouriteScreen(
    listOfFavouriteMovies: List<MovieFavouriteModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(
            key = { movieFavouriteModel ->
                movieFavouriteModel.id
            },
            items = listOfFavouriteMovies,
            itemContent = { movieFavouriteModel ->
                FavouriteMovieItem(
                    movieFavouriteModel
                )
            }
        )
    }
}