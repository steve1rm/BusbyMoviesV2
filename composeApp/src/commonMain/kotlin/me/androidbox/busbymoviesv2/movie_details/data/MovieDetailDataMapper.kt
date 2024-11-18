package me.androidbox.busbymoviesv2.movie_details.data

import me.androidbox.busbymoviesv2.movie_details.data.dto.CastDto
import me.androidbox.busbymoviesv2.movie_details.data.dto.CreditsDto
import me.androidbox.busbymoviesv2.movie_details.data.dto.CrewDto
import me.androidbox.busbymoviesv2.movie_details.data.dto.GenreDto
import me.androidbox.busbymoviesv2.movie_details.data.dto.MovieDetailDto
import me.androidbox.busbymoviesv2.movie_details.data.dto.VideoResultsDto
import me.androidbox.busbymoviesv2.movie_details.domain.models.CastModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.CreditsModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.CrewModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.GenreModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieDetailModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.VideoResultsModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.VideosModel

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
        },
        videos = this.videos.toVideosResultsModel()
    )
}

fun GenreDto.toGenreModel(): GenreModel {
    return GenreModel(
        id = this.id,
        name = this.name)
}

fun CreditsDto.toCreditsModel(): CreditsModel {
    return CreditsModel(
        id = this.id,
        castModel = this.castDto.map { castDto ->
            castDto.toCastModel()
        },
        crewModel = this.crewDto.map { crewDto ->
            crewDto.toCrewModel()
        }
    )
}

fun CastDto.toCastModel(): CastModel {
    return CastModel(
        id = this.id,
        name = this.name,
        character = this.character,
        popularity = this.popularity,
        creditId = this.creditId,
        castId = this.castId,
        profilePath = this.profilePath.orEmpty(),
    )
}

fun CrewDto.toCrewModel(): CrewModel {
    return CrewModel(
        id = this.id,
        name = this.name,
        popularity = this.popularity,
        creditId = this.creditId,
        job = this.job,
        profilePath = this.profilePath.orEmpty(),
    )
}

fun VideoResultsDto.toVideosResultsModel(): VideoResultsModel {
    return VideoResultsModel(
        videosModel = resultsDto.map { videosDto ->
            VideosModel(
                name = videosDto.name,
                key = videosDto.key,
                site = videosDto.site,
                type = videosDto.type,
                id = videosDto.id,
                publishedAt = videosDto.publishedAt
            )
        }
    )
}