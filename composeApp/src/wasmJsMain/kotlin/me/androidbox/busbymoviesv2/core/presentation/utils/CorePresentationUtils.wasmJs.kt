package me.androidbox.busbymoviesv2.core.presentation.utils

import kotlinx.browser.window

actual fun getScreenDensity(): String {
    val devicePixelRatio = window.devicePixelRatio

    return when {
        devicePixelRatio <= 1.0 -> "MDPI"
        devicePixelRatio <= 2.0 -> "XHDPI"
        devicePixelRatio <= 3.0 -> "XXHDPI"
        else -> "XXXHDPI"
    }
}