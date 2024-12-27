package me.androidbox.busbymoviesv2.actors.presentation

import me.androidbox.busbymoviesv2.movie_details.presentation.model.Cast

data class ActorState(
    val actors: List<Cast> = emptyList(),
    val isLoading: Boolean = false
)
