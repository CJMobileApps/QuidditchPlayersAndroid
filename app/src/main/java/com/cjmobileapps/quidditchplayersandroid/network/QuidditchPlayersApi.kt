package com.cjmobileapps.quidditchplayersandroid.network

import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Position
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface QuidditchPlayersApi {

    @GET("api/v1/quidditch/players")
    fun getPlayersAsync(): Deferred<List<Player>>

    @GET("api/v1/quidditch/positions")
    fun getPositionsAsync(): Deferred<List<Position>>
}
