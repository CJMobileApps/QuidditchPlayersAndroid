package com.cjmobileapps.quidditchplayersandroid.network

import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

class QuidditchPlayersService(private val quidditchPlayersApi: QuidditchPlayersApi, private val webSocketRepository: WebSocketRepository) {

    fun getPlayersAsync()  =  quidditchPlayersApi.getPlayersAsync()

    fun getPositionsAsync() = quidditchPlayersApi.getPositionsAsync()

    fun getStatuses(): Flowable<Status> {
        return Flowable.create<Status>({ emitter ->
            webSocketRepository.connectToStatuses(object : WebSocketRepository.StatusListener {
                override fun onStatus(status: Status) {
                    emitter.onNext(status)
                }
            })
        }, BackpressureStrategy.LATEST)
    }

    fun endStatusUpdates(): Boolean {
        return webSocketRepository.disconnectFromStatuses()
    }
}
