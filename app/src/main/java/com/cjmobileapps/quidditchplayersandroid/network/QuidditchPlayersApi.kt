package com.cjmobileapps.quidditchplayersandroid.network

import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Position
import io.reactivex.Single
import retrofit2.http.GET

interface QuidditchPlayersApi {

    @GET("api/v2/quidditch/player")
    fun getPlayers(): Single<List<Player>>

    @GET("api/v2/quidditch/position")
    fun getPositions(): Single<List<Position>>
}
