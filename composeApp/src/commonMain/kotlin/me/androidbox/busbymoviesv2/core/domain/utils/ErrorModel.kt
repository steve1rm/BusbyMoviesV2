package me.androidbox.busbymoviesv2.core.domain.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorModel(
    @SerialName("status_code")
    val statusCode: Int = 0,
    @SerialName("status_message")
    val statusMessage: String = "",
    @SerialName("success")
    val success: Boolean = false
)