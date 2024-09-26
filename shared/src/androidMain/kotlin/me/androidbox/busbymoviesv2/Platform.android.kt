package me.androidbox.busbymoviesv2

import android.os.Build
import me.androidbox.busbymoviesv2.shared.BuildConfig

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun isDebug(): Boolean {
   return BuildConfig.DEBUG
}