package me.androidbox.busbymoviesv2.core.data.network

import co.touchlab.kermit.Logger
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLParserException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import me.androidbox.busbymoviesv2.core.domain.utils.CheckResult
import me.androidbox.busbymoviesv2.core.domain.utils.DataError
import me.androidbox.busbymoviesv2.core.domain.utils.ErrorModel
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <reified D> safeApiRequest(block: () -> HttpResponse): CheckResult<D, DataError.Network, ErrorModel> {
    val httpResponse = try {
        block()
    }
    catch(exception: UnresolvedAddressException) {
        exception.printStackTrace()

        return CheckResult.Failure(DataError.Network.NO_INTERNET)
    }
    catch(exception: SerializationException) {
        exception.printStackTrace()

        return CheckResult.Failure(DataError.Network.SERIALIZATION)
    }
    catch(exception: URLParserException) {
        exception.printStackTrace()
        println("NETWORK STATUS URLParser")
        return CheckResult.Failure(DataError.Network.BAD_REQUEST)
    }
    catch (exception: Exception) {
        exception.printStackTrace()

        if (exception is CancellationException) {
            Logger.e(exception.message.orEmpty(), exception)
            throw exception
        }
        return CheckResult.Failure(DataError.Network.UNKNOWN)
    }

    return responseToResult(httpResponse)
}


suspend inline fun <reified D> responseToResult(response: HttpResponse): CheckResult<D, DataError.Network, ErrorModel> {
    return when(response.status.value) {
        in 200..299 -> {
            CheckResult.Success(response.body<D>())
        }
        400 -> {
            val response1 = response.body<ErrorModel>()
            Logger.d {
                "${response1.statusMessage}"
            }
            CheckResult.Failure(DataError.Network.BAD_REQUEST, response.body<ErrorModel>())
        }
        401 -> {
            CheckResult.Failure(DataError.Network.UNAUTHORIZED, response.body<ErrorModel>())
        }
        408 -> {
            CheckResult.Failure(DataError.Network.REQUEST_TIMEOUT, response.body<ErrorModel>())
        }
        409 -> {
            CheckResult.Failure(DataError.Network.CONFLICT, response.body<ErrorModel>())
        }
        413 -> {
            CheckResult.Failure(DataError.Network.PAYLOAD_TOO_LARGE, response.body<ErrorModel>())
        }
        429 -> {
            CheckResult.Failure(DataError.Network.TOO_MANY_REQUESTS, response.body<ErrorModel>())
        }
        in 500..599 -> {
            CheckResult.Failure(DataError.Network.SERVER_ERROR, response.body<ErrorModel>())
        }
        else -> {
            CheckResult.Failure(DataError.Network.UNKNOWN, response.body<ErrorModel>())
        }
    }
}