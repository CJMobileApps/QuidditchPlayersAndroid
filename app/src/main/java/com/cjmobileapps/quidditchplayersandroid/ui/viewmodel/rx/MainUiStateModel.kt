package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx

import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Status

sealed class MainUiStateModel {

    object IdleState : MainUiStateModel()

    data class GetPlayersMainUiStateModel(
            val inProgress: Boolean,
            val success: Boolean,
            val errorMessage: String? = null,
            val statusCode: Int? = null,
            val playerList: List<Player>? = null) : MainUiStateModel() {

        companion object {

            fun idle(): MainUiStateModel {
                return GetPlayersMainUiStateModel(inProgress = false, success = false)
            }


            fun inProgress(): MainUiStateModel {
                return GetPlayersMainUiStateModel(inProgress = true, success = false)
            }


            fun success(playerList: List<Player>): MainUiStateModel {
                return GetPlayersMainUiStateModel(inProgress = false, success = true, playerList = playerList)
            }


            fun failure(errorMessage: String?, statusCode: Int?): MainUiStateModel {
                return GetPlayersMainUiStateModel(inProgress = false, success = false, errorMessage = errorMessage, statusCode = statusCode)
            }
        }
    }

    data class GetStatusesMainUiStateModel(
            val inProgress: Boolean,
            val success: Boolean,
            val errorMessage: String? = null,
            val statusCode: Int? = null,
            val status: Status? = null) : MainUiStateModel() {

        companion object {

            fun idle() : MainUiStateModel {
                return GetStatusesMainUiStateModel(inProgress = false, success = false)
            }


            fun inProgress() : MainUiStateModel {
                return GetStatusesMainUiStateModel(inProgress = true, success = false)
            }


            fun success(status: Status) : MainUiStateModel {
                return GetStatusesMainUiStateModel(inProgress = false, success = true, status = status)
            }


            fun failure(errorMessage: String?, statusCode: Int?) : MainUiStateModel {
                return GetStatusesMainUiStateModel(inProgress = false, success = false, errorMessage = errorMessage, statusCode = statusCode)
            }
        }
    }

    object EndStatusesMainUiStateModel: MainUiStateModel()
}
