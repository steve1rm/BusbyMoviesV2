package me.androidbox.busbymoviesv2.core.presentation.utils

import java.awt.Toolkit

actual fun getScreenDensity(): String {
    val toolKit = Toolkit.getDefaultToolkit()
    val screenResolution = toolKit.screenResolution

    return when {
        screenResolution <= 120 -> "LDPI"
        screenResolution <= 160 -> "MDPI"
        screenResolution <= 240 -> "HDPI"
        screenResolution <= 320 -> "XHDPI"
        screenResolution <= 480 -> "XXHDPI"
        else -> "XXXHDPI"
    }
}
