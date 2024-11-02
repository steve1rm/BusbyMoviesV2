package me.androidbox.busbymoviesv2.movie_details.presentation.model

data class Cast(
    val id: Int,
    val name: String,
    val popularity: Double,
    val profilePath: String,
    val castId: Int,
    val character: String,
    val creditId: String,
)
