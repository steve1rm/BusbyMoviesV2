package me.androidbox.busbymoviesv2.configuration.domain.models


data class ImagesModel(
    val baseUrl: String = "",
    val secureBaseUrl: String = "",
    val backdropSizes: List<String> = listOf(),
    val logoSizes: List<String> = listOf(),
    val posterSizes: List<String> = listOf(),
    val profileSizes: List<String> = listOf(),
    val stillSizes: List<String> = listOf()
)
