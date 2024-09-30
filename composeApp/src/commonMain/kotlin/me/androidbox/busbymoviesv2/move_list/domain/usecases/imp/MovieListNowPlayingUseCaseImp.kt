package me.androidbox.busbymoviesv2.move_list.domain.usecases.imp

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.data.repository.MovieListRepository
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel
import me.androidbox.busbymoviesv2.move_list.domain.usecases.MovieListNowPlayingUseCase

class MovieListNowPlayingUseCaseImp(
    private val movieListRepository: MovieListRepository
) : MovieListNowPlayingUseCase {
    override suspend fun execute(): CheckResult<MovieListModel, DataError.Network, ErrorModel> {
        return movieListRepository.nowPlaying()
    }
}