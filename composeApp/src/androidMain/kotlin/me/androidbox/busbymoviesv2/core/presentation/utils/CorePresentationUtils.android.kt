package me.androidbox.busbymoviesv2.core.presentation.utils

import android.content.res.Resources

actual fun getScreenDensity(): String {
    val localDensity = Resources.getSystem().displayMetrics.densityDpi

    val screenDensity = when {
        localDensity <= 120 -> "LDPI"
        localDensity <= 160 -> "MDPI"
        localDensity <= 240 -> "HDPI"
        localDensity <= 320 -> "XHDPI"
        localDensity <= 480 -> "XXHDPI"
        else -> "XXXHDPI"
    }

    return screenDensity
}