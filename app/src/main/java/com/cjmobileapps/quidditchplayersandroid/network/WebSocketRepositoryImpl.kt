package com.cjmobileapps.quidditchplayersandroid.network

import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import io.reactivex.Flowable
import io.reactivex.Single

interface WebSocketRepositoryImpl {

    fun getStatuses(): Flowable<Status>

    fun endStatusUpdates(): Single<Boolean>
}
