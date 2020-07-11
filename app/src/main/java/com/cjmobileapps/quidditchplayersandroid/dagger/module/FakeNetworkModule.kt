package com.cjmobileapps.quidditchplayersandroid.dagger.module

import com.cjmobileapps.quidditchplayersandroid.dagger.QuidditchPlayersApplicationScope
import com.cjmobileapps.quidditchplayersandroid.network.*
import com.cjmobileapps.quidditchplayersandroid.network.service.FakeQuidditchPlayersService
import com.cjmobileapps.quidditchplayersandroid.network.service.QuidditchPlayersServiceImpl
import dagger.Module
import dagger.Provides
import okhttp3.*

@Module
open class FakeNetworkModule {

    @QuidditchPlayersApplicationScope
    @Provides
    fun quidditchPlayersService(): QuidditchPlayersServiceImpl {
        return FakeQuidditchPlayersService()
    }

    @QuidditchPlayersApplicationScope
    @Provides
    fun webSocketRepository(okHttpClient: OkHttpClient): WebSocketRepositoryImpl {
        return WebSocketRepository(okHttpClient, "wss://cjmobileapps.com/api/v1/quidditch/status")
    }
}
