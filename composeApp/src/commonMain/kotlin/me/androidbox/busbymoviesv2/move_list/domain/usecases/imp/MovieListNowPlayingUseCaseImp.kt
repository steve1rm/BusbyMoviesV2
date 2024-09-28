package me.androidbox.busbymoviesv2.move_list.domain.usecases.imp

import me.androidbox.busbymoviesv2.move_list.data.repository.MovieListRepository
import me.androidbox.busbymoviesv2.move_list.domain.usecases.MovieListNowPlayingUseCase

class MovieListNowPlayingUseCaseImp(
    private val movieListRepository: MovieListRepository
) : MovieListNowPlayingUseCase{
    override suspend fun execute() {
        movieListRepository.nowPlaying()
    }
}