package me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.GetFavouriteMoviesUseCase

class GetFavouriteMoviesUseCaseImp(private val movieDetailRepository: MovieDetailRepository) : GetFavouriteMoviesUseCase {
    override fun execute(): Flow<List<MovieFavouriteModel>> {
        return movieDetailRepository.getAllFavouriteMovies()
    }
}