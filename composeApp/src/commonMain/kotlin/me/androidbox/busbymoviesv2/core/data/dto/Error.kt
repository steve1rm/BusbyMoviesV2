package me.androidbox.busbymoviesv2.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Error(
    val code: Int = 0,
    val errors: List<Errors> = listOf(),
    val message: String = ""
)
