package me.androidbox.busbymoviesv2.move_list.presentation.models

data class MovieList(
    val datesModel: Dates = Dates(),
    val page: Int = 0,
    val movieResult: List<MovieResult> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)