package me.androidbox.busbymoviesv2

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BusbyMoviesV2",
    ) {
        App()
    }
}