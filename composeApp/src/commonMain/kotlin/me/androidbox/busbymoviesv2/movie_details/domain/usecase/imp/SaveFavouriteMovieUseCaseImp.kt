package me.androidbox.busbymoviesv2.movie_details.domain.usecase.imp

import me.androidbox.busbymoviesv2.movie_details.data.repository.MovieDetailRepository
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieFavouriteModel
import me.androidbox.busbymoviesv2.movie_details.domain.usecase.SaveFavouriteMovieUseCase


class SaveFavouriteMovieUseCaseImp(private val movieDetailRepository: MovieDetailRepository) : SaveFavouriteMovieUseCase {
    override suspend fun execute(movieFavouriteModel: MovieFavouriteModel) {
        movieDetailRepository.insertFavouriteMovie(movieFavouriteModel)
    }
}