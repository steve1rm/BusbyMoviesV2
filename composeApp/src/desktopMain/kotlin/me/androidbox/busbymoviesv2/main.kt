package me.androidbox.busbymoviesv2

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import me.androidbox.busbymoviesv2.di.desktopSpecificModule

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BusbyMoviesV2",
    ) {
        initializeKoin(
            koinConfig = null,
            desktopSpecificModule
        )

        App()
    }
}