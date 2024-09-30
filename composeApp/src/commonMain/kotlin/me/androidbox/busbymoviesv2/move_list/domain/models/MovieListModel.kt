package me.androidbox.busbymoviesv2.move_list.domain.models

data class MovieListModel(
    val datesModel: DatesModel = DatesModel(),
    val page: Int = 0,
    val movieResultModel: List<MovieResultModel> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)