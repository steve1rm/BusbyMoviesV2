package me.androidbox.busbymoviesv2.move_list.presentation

import busbymoviesv2.composeapp.generated.resources.Res
import busbymoviesv2.composeapp.generated.resources.now_playing
import busbymoviesv2.composeapp.generated.resources.popular
import busbymoviesv2.composeapp.generated.resources.top_rated
import busbymoviesv2.composeapp.generated.resources.upcoming
import me.androidbox.busbymoviesv2.core.data.network.Routes
import org.jetbrains.compose.resources.StringResource

enum class MovieCategories(val titleRes: StringResource, val movieRoute: String) {
    NOW_PLAYING(Res.string.now_playing, Routes.NOW_PLAYING),
    TOP_RATED(Res.string.top_rated, Routes.TOP_RATED),
    POPULAR(Res.string.popular, Routes.POPULAR),
    UPCOMING(Res.string.upcoming, Routes.UPCOMING)
}