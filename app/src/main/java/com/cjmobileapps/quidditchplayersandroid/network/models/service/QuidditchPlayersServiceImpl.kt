package com.cjmobileapps.quidditchplayersandroid.network.models.service

import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Position
import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import io.reactivex.Flowable
import io.reactivex.Single

interface QuidditchPlayersServiceImpl {

    fun getPlayers(): Single<List<Player>>

    fun getPositions(): Single<List<Position>>

    fun getStatuses(): Flowable<Status>

    fun endStatusUpdates(): Single<Boolean>
}
