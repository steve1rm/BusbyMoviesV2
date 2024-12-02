package me.androidbox.busbymoviesv2.movie_details.domain.models

data class MovieDetailModel(
    val isAdult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: List<GenreModel>,
    val videos: VideoResultsModel
)