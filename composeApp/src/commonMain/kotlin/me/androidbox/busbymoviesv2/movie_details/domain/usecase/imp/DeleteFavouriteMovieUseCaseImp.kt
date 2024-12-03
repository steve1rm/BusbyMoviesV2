package me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp

import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.DeleteFavouriteMovieUseCase


class DeleteFavouriteMovieUseCaseImp(private val movieDetailRepository: MovieDetailRepository) : DeleteFavouriteMovieUseCase {
    override suspend fun execute(movieFavouriteModel: MovieFavouriteModel) {
        movieDetailRepository.deleteFavouriteMovie(movieFavouriteModel)
    }
}