package com.cjmobileapps.quidditchplayersandroid.network.service

import com.cjmobileapps.quidditchplayersandroid.network.QuidditchPlayersApi
import com.cjmobileapps.quidditchplayersandroid.network.WebSocketRepositoryImpl

class QuidditchPlayersService(private val quidditchPlayersApi: QuidditchPlayersApi, private val webSocketRepository: WebSocketRepositoryImpl) : QuidditchPlayersServiceImpl {

    override fun getPlayers() = quidditchPlayersApi.getPlayers()

    override fun getPositions() = quidditchPlayersApi.getPositions()

    override fun getStatuses() = webSocketRepository.getStatuses()

    override fun endStatusUpdates() = webSocketRepository.endStatusUpdates()
}
