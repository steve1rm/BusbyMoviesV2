package me.androidbox.busbymoviesv2.move_list.data.remote_data_source

interface MoveListRemoteDataSource {
    suspend fun nowPlaying()
    fun popular()
    fun trending()
    fun upcoming()
}