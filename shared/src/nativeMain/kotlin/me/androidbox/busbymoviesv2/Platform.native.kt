package me.androidbox.busbymoviesv2

actual fun isDebug(): Boolean {
    var isDebug = false
    #if DEBUG
    isDebug = true
    #endif

    return isDebug
}