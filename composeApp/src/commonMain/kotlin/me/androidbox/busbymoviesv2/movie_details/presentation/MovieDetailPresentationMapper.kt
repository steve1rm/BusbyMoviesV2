package me.androidbox.busbymoviesv2.movie_details.presentation

import me.androidbox.busbymoviesv2.core.data.network.Routes.BASE_IMAGE_PATH
import me.androidbox.busbymoviesv2.movie_details.domain.models.CastModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.CreditsModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.CrewModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.GenreModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.MovieDetailModel
import me.androidbox.busbymoviesv2.movie_details.domain.models.VideoResultsModel
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Cast
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Credits
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Crew
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Genre
import me.androidbox.busbymoviesv2.movie_details.presentation.model.MovieDetail
import me.androidbox.busbymoviesv2.movie_details.presentation.model.VideoResults
import me.androidbox.busbymoviesv2.movie_details.presentation.model.Videos

fun MovieDetailModel.toMovieDetail(imageSize: String): MovieDetail {
    val imagePath = "$BASE_IMAGE_PATH$imageSize"

    return MovieDetail(
        isAdult = this.isAdult,
        backdropPath = "$imagePath${this.backdropPath}",
        budget = this.budget,
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = "$imagePath${this.posterPath}",
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
        },
        videos = this.videos.toVideosResults()
    )
}

fun GenreModel.toGenre(): Genre {
    return Genre(
        id = this.id,
        name = this.name)
}

fun CreditsModel.toCredits(): Credits {
    return Credits(
        id = this.id,
        cast = this.castModel.map { cast ->
            cast.toCast()
        },
        crew = this.crewModel.map { crew ->
            crew.toCrew()
        }
    )
}

fun CastModel.toCast(): Cast {
    val imagePath = "${BASE_IMAGE_PATH}w500"

    return Cast(
        id = this.id,
        name = this.name,
        character = this.character,
        popularity = this.popularity,
        creditId = this.creditId,
        castId = this.castId,
        profilePath = "$imagePath${this.profilePath}",
    )
}

fun CrewModel.toCrew(): Crew {
    return Crew(
        id = this.id,
        name = this.name,
        popularity = this.popularity,
        creditId = this.creditId,
        job = this.job,
        profilePath = this.profilePath.orEmpty(),
    )
}

fun VideoResultsModel.toVideosResults(): VideoResults {
    return VideoResults(
        results = videosModel.map { videosModel ->
            Videos(
                name = videosModel.name,
                key = videosModel.key,
                site = videosModel.site,
                type = videosModel.type,
                id = videosModel.id,
                publishedAt = videosModel.publishedAt
            )
        }
    )
}