package me.androidbox.busbymoviesv2.movie_details.presentation.model

data class Credits(
    val id: Int,
    val castDto: List<Cast>,
    val crewDto: List<Crew>
)