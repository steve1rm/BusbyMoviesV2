package me.androidbox.busbymoviesv2.movie_details.domain.models

data class CreditsModel(
    val id: Int,
    val castModel: List<CastModel>,
    val crewModel: List<CrewModel>
)