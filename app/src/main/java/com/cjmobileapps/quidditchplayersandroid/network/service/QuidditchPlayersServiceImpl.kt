package com.cjmobileapps.quidditchplayersandroid.network.service

import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import com.cjmobileapps.quidditchplayersandroid.network.util.ResponseWrapper
import io.reactivex.Flowable
import io.reactivex.Single

interface QuidditchPlayersServiceImpl {

    fun getPlayers(): Single<ResponseWrapper<List<Player>>>

    fun getPositions(): Single<ResponseWrapper<Map<String, String>>>

    fun getStatuses(): Flowable<Status>

    fun endStatusUpdates(): Single<Boolean>
}
