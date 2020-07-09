package com.cjmobileapps.quidditchplayersandroid.dagger.module

import android.content.Context
import com.cjmobileapps.quidditchplayersandroid.dagger.QuidditchPlayersApplicationScope
import com.cjmobileapps.quidditchplayersandroid.network.*
import com.cjmobileapps.quidditchplayersandroid.network.models.service.FakeQuidditchPlayersService
import com.cjmobileapps.quidditchplayersandroid.network.models.service.QuidditchPlayersServiceImpl
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

@Module
open class FakeNetworkModule {
    //Create a cache object part 1.
    @QuidditchPlayersApplicationScope
    @Provides
    open fun httpCacheDirectory(context: Context): File {
        return File(context.getCacheDir(), NetworkConstants.HTTP_CACHE_DIR)
    }

    //Create a cache object part 2.
    @QuidditchPlayersApplicationScope
    @Provides
    open fun cache(httpCacheDirectory: File): Cache {
        return Cache(httpCacheDirectory, NetworkConstants.HTTP_CACHE_SIZE)
    }

    //Create a network cache interceptor, setting the max age to 1 minute
    @QuidditchPlayersApplicationScope
    @Provides
    open fun networkCacheInterceptor(): Interceptor {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val response = chain.proceed(chain.request())

                val cacheControl = CacheControl.Builder()
                        .maxAge(1, TimeUnit.MINUTES)
                        .build()

                return response.newBuilder()
                        .header(NetworkConstants.CACHE_CONTROL, cacheControl.toString()).build()
            }
        }
    }

    //Create a logging interceptor
    @QuidditchPlayersApplicationScope
    @Provides
    open fun loggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        return loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    //Create the httpClient, configure it
    // with cache, network cache interceptor and logging interceptor
    @QuidditchPlayersApplicationScope
    @Provides
    open fun okHttpClient(cache: Cache,
                          networkCacheInterceptor: Interceptor,
                          loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(networkCacheInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
    }

    //Create the Retrofit with the httpClient
    @QuidditchPlayersApplicationScope
    @Provides
    open fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://cjmobileapps.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @QuidditchPlayersApplicationScope
    @Provides
    open fun quidditchPlayersApi(retrofit: Retrofit): QuidditchPlayersApi {
        return retrofit.create(QuidditchPlayersApi::class.java)
    }

    @QuidditchPlayersApplicationScope
    @Provides
    open fun quidditchPlayersService(quidditchPlayersApi: QuidditchPlayersApi, webSocketRepository: WebSocketRepositoryImpl): QuidditchPlayersServiceImpl {
        return FakeQuidditchPlayersService()
    }

    @QuidditchPlayersApplicationScope
    @Provides
    open fun webSocketRepository(okHttpClient: OkHttpClient): WebSocketRepositoryImpl {
        return WebSocketRepository(okHttpClient, "wss://cjmobileapps.com/api/v1/quidditch/status")
    }
}
