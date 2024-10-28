package me.androidbox.busbymoviesv2.movie_details.data

import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Int,
    val name: String
)
