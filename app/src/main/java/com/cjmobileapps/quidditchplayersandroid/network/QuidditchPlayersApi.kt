package com.cjmobileapps.quidditchplayersandroid.network

import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Position
import io.reactivex.Single
import retrofit2.http.GET

interface QuidditchPlayersApi {

    @GET("api/v1/quidditch/players")
    fun getPlayers(): Single<List<Player>>

    @GET("api/v1/quidditch/positions")
    fun getPositions(): Single<List<Position>>
}