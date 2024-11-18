package me.androidbox.busbymoviesv2.movie_details.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosDto(
    val name: String,
    val key: String,
    val site: String,
    val type: String,
    val id: String,
    @SerialName("published_at")
    val publishedAt: String)
