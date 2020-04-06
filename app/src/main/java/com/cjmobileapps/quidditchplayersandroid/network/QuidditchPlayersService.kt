package com.cjmobileapps.quidditchplayersandroid.network

class QuidditchPlayersService(private val quidditchPlayersApi: QuidditchPlayersApi, private val webSocketRepository: WebSocketRepository) {

    fun getPlayers() = quidditchPlayersApi.getPlayers()

    fun getPositions() = quidditchPlayersApi.getPositions()

    fun getStatuses() = webSocketRepository.getStatuses()

    fun endStatusUpdates() = webSocketRepository.endStatusUpdates()
}
