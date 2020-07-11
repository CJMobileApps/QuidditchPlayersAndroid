package com.cjmobileapps.quidditchplayersandroid.network.util

data class ResponseWrapper<T>(
        val data: T? = null,
        val error: Error? = null,
        val statusCode: Int
)

data class Error(
        val isError: Boolean = false,
        val message: String? = null
)
