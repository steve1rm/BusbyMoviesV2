package me.androidbox.busbymoviesv2.move_list.data.repository

interface MovieListRepository {
    suspend fun nowPlaying()
    fun popular()
    fun trending()
    fun upcoming()
}