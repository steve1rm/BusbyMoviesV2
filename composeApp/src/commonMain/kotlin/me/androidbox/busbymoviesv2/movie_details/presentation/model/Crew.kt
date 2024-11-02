package me.androidbox.busbymoviesv2.movie_details.presentation.model

data class Crew(
    val id: Int,
    val name: String,
    val popularity: Double,
    val profilePath: String,
    val creditId: String,
    val job: String
)