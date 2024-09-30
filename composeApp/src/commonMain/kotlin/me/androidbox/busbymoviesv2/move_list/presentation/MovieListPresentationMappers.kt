package me.androidbox.busbymoviesv2.move_list.presentation

import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel
import me.androidbox.busbymoviesv2.move_list.presentation.models.MovieResult

fun MovieListModel.toMovieList(): List<MovieResult> {

    return this.movieResultModel.map {
        MovieResult(
            adult = it.adult,
            backdropPath = it.backdropPath,
            genreIds = it.genreIds,
            id = it.id,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            video = it.video,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount
        )
    }
}