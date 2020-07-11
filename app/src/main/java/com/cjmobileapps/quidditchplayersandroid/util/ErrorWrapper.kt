package com.cjmobileapps.quidditchplayersandroid.util

import retrofit2.HttpException
import timber.log.Timber
import java.io.PrintWriter
import java.io.StringWriter

data class ErrorWrapper(var throwable: Throwable) {

    val statusCode: Int
        get() {
            if (throwable is HttpException) {
                return (throwable as HttpException).code()
            }

            return Int.MIN_VALUE
        }

    val message: String
        get() {
            if (throwable is HttpException) {
                var message = (throwable as HttpException).message()
                if (message.isNullOrEmpty()) {
                    message = "Network call error"
                }

                return message
            }

            val stringWriter = StringWriter()
            val printWriter = PrintWriter(stringWriter)
            throwable.printStackTrace(printWriter)


            Timber.tag("ErrorWrapper() ").e(stringWriter.toString())

            return throwable.message ?: ""
        }
}

fun Throwable.toErrorWrapper(): ErrorWrapper {
    return ErrorWrapper(this)
}