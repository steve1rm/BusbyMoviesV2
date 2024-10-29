package me.androidbox.busbymoviesv2.core.presentation.utils

import androidx.compose.ui.graphics.Color
import me.androidbox.busbymoviesv2.configuration.domain.models.ConfigurationModel
import me.androidbox.busbymoviesv2.core.data.network.Routes.BASE_IMAGE_PATH
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


/**
 * Appends the provided image size to the base image path and returns the full poster URL.
 *
 * @param imageSize The desired image size for the poster.
 * @return The full poster URL with the specified image size.
 */
fun String.toMovieWithImageSize(imageSize: String): String {
    val imagePath = "$BASE_IMAGE_PATH$imageSize"
    return "$imagePath$this"
}
