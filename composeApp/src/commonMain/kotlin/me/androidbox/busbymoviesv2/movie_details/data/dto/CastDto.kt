package me.androidbox.busbymoviesv2.movie_details.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastDto(
    val id: Int,
    val name: String,
    val popularity: Double,
    @SerialName("profile_path")
    val profilePath: String,
    @SerialName("cast_id")
    val castId: Int,
    val character: String,
    @SerialName("credit_id")
    val creditId: String,
)
