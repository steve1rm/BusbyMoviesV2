package me.androidbox.busbymoviesv2.favourites.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.androidbox.busbymoviesv2.core.presentation.components.SwipeToDeleteContainer
import me.androidbox.busbymoviesv2.favourites.presentation.FavouriteActions
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

@Composable
fun FavouriteScreen(
    listOfFavouriteMovies: MutableList<MovieFavouriteModel>,
    onMovieClicked: (movieId: Int) -> Unit,
    onFavouriteActions: (action: FavouriteActions) -> Unit,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        if(listOfFavouriteMovies.isEmpty()) {
            Text(text = "No favourites have been added",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold)
        }
        else {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {
                items(
                    key = { movieFavouriteModel ->
                        movieFavouriteModel.id
                    },
                    items = listOfFavouriteMovies,
                    itemContent = { movieFavouriteModel ->
                        SwipeToDeleteContainer(
                            item = movieFavouriteModel,
                            onDelete = { item ->
                                onFavouriteActions(FavouriteActions.OnDeleteFromFavourites(item))
                            },
                            content = {
                                FavouriteMovieItem(
                                    movieFavouriteModel,
                                    onMovieClicked = { movieId ->
                                        onMovieClicked(movieId)
                                    },
                                    onMovieDelete = { movieFavouriteModel ->
                                        onFavouriteActions(FavouriteActions.OnDeleteFromFavourites(movieFavouriteModel))
                                    }
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}