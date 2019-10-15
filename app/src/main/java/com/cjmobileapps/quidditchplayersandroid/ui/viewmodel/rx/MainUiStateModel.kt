package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx

import com.cjmobileapps.quidditchplayersandroid.network.models.Player

data class MainUiStateModel(
        val inProgress: Boolean,
        val success: Boolean,
        val errorMessage: String? = null,
        val statusCode: Int? = null,
        val playerList: List<Player>? = null) {

    companion object {

        fun idle() = MainUiStateModel(inProgress = false, success = false)


        fun inProgress() = MainUiStateModel(inProgress = true, success = false)


        fun success(playerList: List<Player>) =
                MainUiStateModel(inProgress = false, success = true, playerList = playerList)


        fun failure(errorMessage: String?, statusCode: Int?) =
                MainUiStateModel(inProgress = false, success = false, errorMessage = errorMessage, statusCode = statusCode)
    }
}
