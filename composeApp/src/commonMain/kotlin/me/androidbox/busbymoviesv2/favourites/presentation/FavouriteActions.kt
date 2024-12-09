package me.androidbox.busbymoviesv2.favourites.presentation

import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

sealed interface FavouriteActions {

    data class OnDeleteFromFavourites(
        val movieFavouriteModel: MovieFavouriteModel
    ) : FavouriteActions
}
