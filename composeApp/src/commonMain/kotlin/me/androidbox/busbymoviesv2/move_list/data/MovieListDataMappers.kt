package me.androidbox.busbymoviesv2.move_list.data

import me.androidbox.busbymoviesv2.move_list.data.dto.DatesDto
import me.androidbox.busbymoviesv2.move_list.data.dto.MovieListDto
import me.androidbox.busbymoviesv2.move_list.data.dto.MovieResultDto
import me.androidbox.busbymoviesv2.move_list.domain.models.DatesModel
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieListModel
import me.androidbox.busbymoviesv2.move_list.domain.models.MovieResultModel

fun DatesModel.toDatesDto(): DatesDto {
    return DatesDto(
        maximum = this.maximum,
        minimum = this.minimum
    )
}

fun MovieListModel.toMovieListDto(): MovieListDto {
    return MovieListDto(
        datesDto = this.datesModel.toDatesDto(),
        page = this.page,
        movieResultDto = this.movieResultModel.map { movieResultModel ->
           movieResultModel.toMovieResultDto()
        },
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

fun MovieResultModel.toMovieResultDto(): MovieResultDto {
    return MovieResultDto(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds.map { it },
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}


/**
 * Converts a [DatesDto] object to a [DatesModel] object.
 *
 * @return A [DatesModel] object with the same maximum and minimum dates as the [DatesDto] object.
 */
fun DatesDto.toDatesModel(): DatesModel {
    return DatesModel(
        maximum = this.maximum,
        minimum = this.minimum
    )
}

fun MovieListDto.toMovieListModel(): MovieListModel {
    return MovieListModel(
        datesModel = this.datesDto.toDatesModel(),
        page = this.page,
        movieResultModel = this.movieResultDto.map { movieResultModel ->
            movieResultModel.toMovieResultModel()
        },
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

fun MovieResultDto.toMovieResultModel(): MovieResultModel {
    return MovieResultModel(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds.map { it },
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}