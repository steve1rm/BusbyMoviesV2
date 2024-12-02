package me.androidbox.busbymoviesv2.movie_details.domain.models

data class MovieFavouriteModel(
    val id: Int,
    val title: String,
    val tagline: String,
    val releaseDate: String,
    val voteCount: Int,
    val voteAverage: Double,
    val dateAdded: Long
)
