package me.androidbox.busbymoviesv2.movie_details.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoResultsDto(
    @SerialName("results")
    val resultsDto: List<VideosDto>
)
