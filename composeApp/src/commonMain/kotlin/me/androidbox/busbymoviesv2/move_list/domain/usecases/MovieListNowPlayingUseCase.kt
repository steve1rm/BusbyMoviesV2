package me.androidbox.busbymoviesv2.move_list.domain.usecases

fun interface MovieListNowPlayingUseCase {
    suspend fun execute()
}