package me.androidbox.busbymoviesv2

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {

        initializeKoin(
            koinConfig = {
                modules(
                )
            }
        )

        App()
    }
}