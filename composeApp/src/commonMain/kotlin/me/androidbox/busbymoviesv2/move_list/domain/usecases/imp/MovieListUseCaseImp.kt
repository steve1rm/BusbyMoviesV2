package me.androidbox.busbymoviesv2.move_list.domain.usecases.imp

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.data.repository.MovieListRepository
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel
import me.androidbox.busbymoviesv2.move_list.domain.usecases.MovieListUseCase

class MovieListUseCaseImp(
    private val movieListRepository: MovieListRepository
) : MovieListUseCase {
    override suspend fun execute(movieRoute: String): CheckResult<MovieListModel, DataError.Network, ErrorModel> {
        return movieListRepository.movieList(movieRoute)
    }
}