package me.androidbox.busbymoviesv2.configuration.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesDto(
    @SerialName("base_url")
    val baseUrl: String = "",
    @SerialName("secure_base_url")
    val secureBaseUrl: String = "",
    @SerialName("backdrop_sizes")
    val backdropSizes: List<String> = listOf(),
    @SerialName("logo_sizes")
    val logoSizes: List<String> = listOf(),
    @SerialName("poster_sizes")
    val posterSizes: List<String> = listOf(),
    @SerialName("profile_sizes")
    val profileSizes: List<String> = listOf(),
    @SerialName("still_sizes")
    val stillSizes: List<String> = listOf()
)
