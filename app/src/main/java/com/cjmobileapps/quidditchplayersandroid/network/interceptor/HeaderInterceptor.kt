package com.cjmobileapps.quidditchplayersandroid.network.interceptor

import com.cjmobileapps.quidditchplayersandroid.network.NetworkConstants
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", NetworkConstants.QUIDDITCH_TOKEN_API_KEY)
                .build()
        return chain.proceed(request)
    }
}
