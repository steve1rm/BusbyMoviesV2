package me.androidbox.busbymoviesv2.movie_details.presentation

import me.androidbox.busbymoviesv2.movie_details.domain.models.GenreModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieDetailModel
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Genre
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail

fun MovieDetailModel.toMovieDetail(): MovieDetail {
    return MovieDetail(
        isAdult = this.isAdult,
        backdropPath = this.backdropPath,
        budget = this.budget,
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        genres = this.genres.map { genreModel ->
            genreModel.toGenre()
        }
    )
}

fun GenreModel.toGenre(): Genre {
    return Genre(
        id = this.id,
        name = this.name)
}