package me.androidbox.busbymoviesv2.core.domain.utils

sealed interface CheckResult<out D, out E, out T> {
    data class Success<D>(val data: D)
        : CheckResult<D, Nothing, Nothing>

    data class Failure<out E: Error, T>(val exceptionError: Error = DataError.Network.NOTHING, val responseError: T? = null)
        : CheckResult<Nothing, E, T>
}

inline fun <D, E: Error, T> CheckResult<D, E, T>.onSuccess(block: (D) -> Unit): CheckResult<D, E, T> {
    return when(this) {
        is CheckResult.Success -> {
            block(this.data)
            this
        }
        else -> {
            this
        }
    }
}

inline fun <D, E: Error, T> CheckResult<D, E, T>.onFailure(block: (Error, T?) -> Unit): CheckResult<D, E, T> {
    return when(this) {
        is CheckResult.Failure -> {
            block(this.exceptionError, this.responseError)
            this
        }
        else -> {
            this
        }
    }
}