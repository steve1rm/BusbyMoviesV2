package me.androidbox.busbymoviesv2.movie_details.data

import me.androidbox.busbymoviesv2.movie_details.data.dto.GenreDto
import me.androidbox.busbymoviesv2.movie_details.data.dto.MovieDetailDto
import me.androidbox.busbymoviesv2.movie_details.domain.models.GenreModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieDetailModel

fun MovieDetailDto.toMovieDetailModel(): MovieDetailModel {
    return MovieDetailModel(
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
        genres = this.genres.map { genreDto ->
            genreDto.toGenreModel()
        }
    )
}

fun GenreDto.toGenreModel(): GenreModel {
    return GenreModel(
        id = this.id,
        name = this.name)
}