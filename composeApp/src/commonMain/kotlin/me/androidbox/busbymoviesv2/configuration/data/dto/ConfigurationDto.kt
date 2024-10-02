package me.androidbox.busbymoviesv2.configuration.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationDto(
    @SerialName("images")
    val images: ImagesDto = ImagesDto(),
    @SerialName("change_keys")
    val changeKeys: List<String> = listOf()
)