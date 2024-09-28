package me.androidbox.busbymoviesv2.move_list.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DatesModel(
    @SerialName("maximum")
    val maximum: String = "",
    @SerialName("minimum")
    val minimum: String = ""
)