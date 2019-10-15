package com.cjmobileapps.quidditchplayersandroid.network

class QuidditchPlayersService(private val quidditchPlayersApi: QuidditchPlayersApi) {

    fun getPlayers() = quidditchPlayersApi.getPlayers()

    fun getPositions() = quidditchPlayersApi.getPositions()
}
