package com.cjmobileapps.quidditchplayersandroid.network

import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single

class QuidditchPlayersService(private val quidditchPlayersApi: QuidditchPlayersApi, private val webSocketRepository: WebSocketRepository) {

    fun getPlayers() = quidditchPlayersApi.getPlayers()

    fun getPositions() = quidditchPlayersApi.getPositions()

    fun getStatuses(): Flowable<Status> {
        return Flowable.create<Status>({ emitter ->
            webSocketRepository.connectToStatuses(object : WebSocketRepository.StatusListener {
                override fun onStatus(status: Status) {
                    emitter.onNext(status)
                }
            })
        }, BackpressureStrategy.LATEST)
    }

    fun endStatusUpdates(): Single<Boolean> {
        return Single.create<Boolean> { emitter ->
            emitter.onSuccess(webSocketRepository.disconnectFromStatuses())
        }
    }
}
