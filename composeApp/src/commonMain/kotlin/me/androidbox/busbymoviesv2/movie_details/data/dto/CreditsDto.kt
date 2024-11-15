package me.androidbox.busbymoviesv2.movie_details.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsDto(
    val id: Int,
    @SerialName("cast")
    val castDto: List<CastDto>,
    @SerialName("crew")
    val crewDto: List<CrewDto>
)