package me.androidbox.busbymoviesv2.move_list.presentation

import me.androidbox.busbymoviesv2.core.data.network.Routes

enum class MovieCategories(val movieRoute: String) {
    NOW_PLAYING(Routes.NOW_PLAYING),
    TRENDING(Routes.TRENDING),
    POPULAR(Routes.POPULAR),
    UPCOMING(Routes.UPCOMING)
}