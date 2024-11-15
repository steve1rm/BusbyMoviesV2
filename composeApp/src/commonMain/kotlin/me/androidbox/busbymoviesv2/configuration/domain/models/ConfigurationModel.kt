package me.androidbox.busbymoviesv2.configuration.domain.models

data class ConfigurationModel(
    val imagesModel: ImagesModel = ImagesModel(),
    val changeKeys: List<String> = listOf()
)