package me.androidbox.busbymoviesv2.movie_details.domain.models

data class CastModel(
    val id: Int,
    val name: String,
    val popularity: Double,
    val profilePath: String,
    val castId: Int,
    val character: String,
    val creditId: String,
)
