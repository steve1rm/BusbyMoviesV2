package me.androidbox.busbymoviesv2.core.data.network

object Routes {
    private const val BASE_URL = "https://api.themoviedb.org/3"
    const val BASE_IMAGE_PATH = "https://image.tmdb.org/t/p/"

    /** Move lists */
    const val NOW_PLAYING = "$BASE_URL/movie/now_playing"
    const val TOP_RATED = "$BASE_URL/movie/top_rated"
    const val POPULAR = "$BASE_URL/movie/popular"
    const val UPCOMING = "$BASE_URL/movie/upcoming"

    /** Movie details */
    const val MOVIE_DETAIL = "$BASE_URL/movie"

    /** Configuration */
    const val CONFIGURATION = "$BASE_URL/configuration"

    /** Authentication Endpoints google */
    private const val BASE_URL_SECURE = "https://securetoken.googleapis.com/v1"

    /** Not used at the moment for google authentication */
    const val SIGN_UP = "$BASE_URL/accounts:signUp"
    const val SIGN_IN_WITH_PASSWORD = "$BASE_URL/accounts:signInWithPassword"
    const val SIGN_IN_WITH_IDP = "$BASE_URL/accounts:signInWithIdp"
    const val TOKEN = "$BASE_URL_SECURE/token"
}