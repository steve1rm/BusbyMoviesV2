package me.androidbox.busbymoviesv2.core.presentation.utils

import androidx.compose.ui.graphics.Color
import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import kotlin.random.Random

expect fun getScreenDensity(): String


fun getRandomColor(): Color {
    val red = Random.nextInt(256)
    val green = Random.nextInt(256)
    val blue = Random.nextInt(256)
    return Color(red, green, blue)
}

fun mapImageSize(configurationResult: ConfigurationModel): String {
    val listOfPosterSizes = configurationResult.imagesModel.posterSizes

    val deviceSize = when (getScreenDensity()) {
        "LDPI" -> "w92"
        "MDPI" -> "w154"
        "HDPI" -> "w185"
        "XHDPI" -> "w342"
        "XXHDPI" -> "w500"
        "XXXHDPI" -> "w780"
        else -> "original"
    }

    val size = listOfPosterSizes.firstOrNull { posterSize ->
        posterSize == deviceSize
    } ?: run {
        "w500"
    }
    return size
}
