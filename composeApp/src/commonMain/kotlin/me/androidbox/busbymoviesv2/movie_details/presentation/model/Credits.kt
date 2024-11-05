package me.androidbox.busbymoviesv2.movie_details.presentation.model

data class Credits(
    val id: Int = 0,
    val cast: List<Cast> = emptyList(),
    val crew: List<Crew> = emptyList()
)