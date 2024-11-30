package me.androidbox.busbymoviesv2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
actual fun VideoPlayer(modifier: Modifier, url: String) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    println("VideoPlayer")
    val youtubePlayerView = remember { YouTubePlayerView(context) }

    val youTubePlayerListener = object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            super.onReady(youTubePlayer)
            println("VideoPlayer ready")
            youTubePlayer.cueVideo(url, 0f)
        }
    }

    DisposableEffect(lifecycleOwner, youtubePlayerView) {
        youtubePlayerView.addYouTubePlayerListener(youTubePlayerListener)
        println("VideoPlayer add")

        onDispose {
            youtubePlayerView.removeYouTubePlayerListener(youTubePlayerListener)
            println("VideoPlayer remove")
        }
    }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            youtubePlayerView
        },
        update = {}
    )
}