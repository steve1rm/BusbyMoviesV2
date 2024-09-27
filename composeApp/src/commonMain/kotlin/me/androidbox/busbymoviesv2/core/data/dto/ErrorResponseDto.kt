package me.androidbox.busbymoviesv2.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseDto(
    val error: Error = Error()
)