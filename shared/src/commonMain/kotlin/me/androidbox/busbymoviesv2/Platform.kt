package me.androidbox.busbymoviesv2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun isDebug(): Boolean