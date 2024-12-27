package me.androidbox.busbymoviesv2.favourites.domain.imp

import kotlinx.coroutines.flow.Flow
import me.androidbox.busbymoviesv2.favourites.domain.GetFavouriteMoviesUseCase
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

class GetFavouriteMoviesUseCaseImp(private val movieDetailRepository: MovieDetailRepository) :
    GetFavouriteMoviesUseCase {
    override fun execute(): Flow<List<MovieFavouriteModel>> {
        return movieDetailRepository.getAllFavouriteMovies()
    }
}