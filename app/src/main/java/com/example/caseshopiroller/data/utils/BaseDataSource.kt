package com.example.caseshopiroller.data.utils

import com.example.caseshopiroller.utils.Resource
import retrofit2.Response
import java.util.concurrent.CancellationException

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            val error = traceErrorException(null)
            return makeError(
                message = error.getErrorMessage(),
            )
        } catch (err: Exception) {
            val error = traceErrorException(err)
            return makeError(
                message = error.getErrorMessage(),
            )
        } catch (e: CancellationException) {
            val error = traceErrorException(e)
            return makeError(
                message = error.getErrorMessage(),
            )
        }
    }

    private fun <T> makeError(message: String): Resource<T> {
        return Resource.error(message)
    }
}