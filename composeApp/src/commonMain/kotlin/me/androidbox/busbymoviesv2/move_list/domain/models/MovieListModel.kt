package me.androidbox.busbymoviesv2.move_list.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListModel(
    @SerialName("dates")
    val datesModel: DatesModel = DatesModel(),
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val movieResultModel: List<MovieResultModel> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)