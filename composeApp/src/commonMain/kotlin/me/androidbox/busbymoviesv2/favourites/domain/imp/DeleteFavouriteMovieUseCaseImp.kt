package me.androidbox.busbymoviesv2.favourites.domain.imp

import me.androidbox.busbymoviesv2.favourites.domain.DeleteFavouriteMovieUseCase
import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel

class DeleteFavouriteMovieUseCaseImp(private val movieDetailRepository: MovieDetailRepository) :
    DeleteFavouriteMovieUseCase {
    override suspend fun execute(movieFavouriteModel: MovieFavouriteModel) {
        movieDetailRepository.deleteFavouriteMovie(movieFavouriteModel)
    }
}