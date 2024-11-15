package me.androidbox.busbymoviesv2.movie_details.domain.usecase

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.Error
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel

fun interface SimilarMoviesUseCase {
    suspend fun execute(movieId: Int): CheckResult<MovieListModel, Error, ErrorModel>
}