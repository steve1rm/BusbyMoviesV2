package me.androidbox.busbymoviesv2.core.data.network

object Routes {
    private const val BASE_URL = "https://api.themoviedb.org/3"

    /** Move lists */
    const val NOW_PLAYING = "$BASE_URL/movie/now_playing"

    /** Configuration */
    const val CONFIGURATION = "$BASE_URL/configuration"

    /** Authentication Endpoints google */
    private const val BASE_URL_SECURE = "https://securetoken.googleapis.com/v1"

    const val SIGN_UP = "$BASE_URL/accounts:signUp"
    const val SIGN_IN_WITH_PASSWORD = "$BASE_URL/accounts:signInWithPassword"
    const val SIGN_IN_WITH_IDP = "$BASE_URL/accounts:signInWithIdp"
    const val TOKEN = "$BASE_URL_SECURE/token"
}