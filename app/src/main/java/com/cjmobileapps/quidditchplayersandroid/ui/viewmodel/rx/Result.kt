package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx

import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Position
import retrofit2.HttpException

sealed class Result {

    sealed class GetPlayersResult : Result() {
        data class Success(val players: List<Player>) : GetPlayersResult()

        //TODO add failure wrapper
        data class Failure(var throwable: Throwable) : GetPlayersResult() {

            val statusCode: Int
                get() {
                    if (throwable is HttpException) {
                        return (throwable as HttpException).code()
                    }

                    return Int.MIN_VALUE
                }

            val message: String
                get() {
                    if (throwable is HttpException) {
                        var message = (throwable as HttpException).message()
                        if (message.isNullOrEmpty()) {
                            message = "Network call error"
                        }

                        return message
                    }

                    return throwable.message ?: ""
                }
        }

        object InProgress : GetPlayersResult()
    }

    sealed class GetPositionsResult : Result() {
        data class Success(val positions: List<Position>) : GetPositionsResult()

        //TODO add failure wrapper
        data class Failure(var throwable: Throwable) : GetPositionsResult() {

            val statusCode: Int
                get() {
                    if (throwable is HttpException) {
                        return (throwable as HttpException).code()
                    }

                    return Int.MIN_VALUE
                }

            val message: String
                get() {
                    if (throwable is HttpException) {
                        var message = (throwable as HttpException).message()
                        if (message.isNullOrEmpty()) {
                            message = "Network call error"
                        }

                        return message
                    }

                    return throwable.message ?: ""
                }
        }

        object InProgress : GetPositionsResult()
    }

    sealed class GetPlayersAndPositionsResult : Result() {
        data class Success(val players: List<Player>) : GetPlayersAndPositionsResult()

        //TODO add failure wrapper
        data class Failure(var throwable: Throwable) : GetPlayersAndPositionsResult() {

            val statusCode: Int
                get() {
                    if (throwable is HttpException) {
                        return (throwable as HttpException).code()
                    }

                    return Int.MIN_VALUE
                }

            val message: String
                get() {
                    if (throwable is HttpException) {
                        var message = (throwable as HttpException).message()
                        if (message.isNullOrEmpty()) {
                            message = "Network call error"
                        }

                        return message
                    }

                    return throwable.message ?: ""
                }
        }

        object InProgress : GetPlayersAndPositionsResult()
    }
}
