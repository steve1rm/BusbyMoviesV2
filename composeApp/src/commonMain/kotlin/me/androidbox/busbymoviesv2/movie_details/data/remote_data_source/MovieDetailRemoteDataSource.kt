package me.androidbox.busbymoviesv2.movie_details.data.remote_data_source

import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import me.androidbox.busbymoviesv2.move_list.data.dto.MovieListDto
import me.androidbox.busbymoviesv2.movie_details.data.dto.CreditsDto
import me.androidbox.busbymoviesv2.movie_details.data.dto.MovieDetailDto

interface MovieDetailRemoteDataSource {
    suspend fun movieDetails(movieId: Int): CheckResult<MovieDetailDto, DataError.Network, ErrorModel>
    suspend fun movieCredits(movieId: Int): CheckResult<CreditsDto, DataError.Network, ErrorModel>
    suspend fun similarMovies(movieId: Int): CheckResult<MovieListDto, DataError.Network, ErrorModel>
}