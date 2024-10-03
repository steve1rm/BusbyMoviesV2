package me.androidbox.busbymoviesv2.core.presentation.utils

actual fun getScreenDensity(): String {
    /**
    As this is desktop always return the largest
    val toolKit = Toolkit.getDefaultToolkit()
    val screenResolution = toolKit.screenResolution
    val devicePixelRatio = window.devicePixelRatio

    return when {
        devicePixelRatio <= 1.0 -> "MDPI"
        devicePixelRatio <= 1.5 -> "HDPI"
        devicePixelRatio <= 2.0 -> "XHDPI"
        devicePixelRatio <= 3.0 -> "XXHDPI"
        else -> "XXXHDPI"
    }
     **/
    return "XXXHDPI"
}