package me.androidbox.busbymoviesv2.move_list.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListDto(
    @SerialName("dates")
    val datesDto: DatesDto = DatesDto(),
    @SerialName("page")
    val page: Int = 0,
    @SerialName("results")
    val movieResultDto: List<MovieResultDto> = listOf(),
    @SerialName("total_pages")
    val totalPages: Int = 0,
    @SerialName("total_results")
    val totalResults: Int = 0
)