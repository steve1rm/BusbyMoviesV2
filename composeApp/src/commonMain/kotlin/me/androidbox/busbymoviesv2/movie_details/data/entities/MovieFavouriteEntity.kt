package me.androidbox.busbymoviesv2.movie_details.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieFavouriteEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val tagline: String,
    val releaseDate: String,
    val voteCount: Int,
    val voteAverage: Double,
    val dateAdded: Long
)
