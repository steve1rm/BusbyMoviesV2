package domain.utils

sealed interface DataError : Error {

    enum class Network : DataError {
        REQUEST_TIMEOUT,
        BAD_REQUEST,
        UNAUTHORIZED,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        CONFLICT,
        UNKNOWN,
        NOTHING,
    }

    enum class Local : DataError {
        DISK_FULL
    }
}