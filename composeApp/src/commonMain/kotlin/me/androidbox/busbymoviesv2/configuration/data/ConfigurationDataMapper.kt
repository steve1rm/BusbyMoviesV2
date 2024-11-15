package me.androidbox.busbymoviesv2.configuration.data

import me.androidbox.busbymoviesv2.configuration.data.dto.ConfigurationDto
import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import me.androidbox.busbymoviesv2.configuration.domain.models.ImagesModel

fun ConfigurationDto.toConfigurationModel(): ConfigurationModel {

    return ConfigurationModel(
        changeKeys = this.changeKeys,
        imagesModel = ImagesModel(
            baseUrl = this.images.baseUrl,
            stillSizes = this.images.stillSizes,
            posterSizes = this.images.posterSizes,
            backdropSizes = this.images.backdropSizes,
            logoSizes = this.images.logoSizes,
            secureBaseUrl = this.images.secureBaseUrl,
            profileSizes = this.images.profileSizes
        )
    )
}