package me.androidbox.busbymoviesv2.move_list.presentation

import me.androidbox.busbymoviesv2.core.data.network.Routes.BASE_IMAGE_PATH
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel
import me.androidbox.busbymoviesv2.move_list.presentation.models.MovieResult

fun MovieListModel.toMovieList(backdropImageSize: String): List<MovieResult> {
    val imagePath = "$BASE_IMAGE_PATH$backdropImageSize/"

    return this.movieResultModel.map {
        MovieResult(
            adult = it.adult,
            backdropPath = "$imagePath${it.backdropPath}",
            genreIds = it.genreIds,
            id = it.id,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = "$imagePath${it.posterPath}",
            releaseDate = it.releaseDate,
            title = it.title,
            video = it.video,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount
        )
    }
}