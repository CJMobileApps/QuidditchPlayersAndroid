package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cjmobileapps.quidditchplayersandroid.network.QuidditchPlayersService
import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Position
import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import com.cjmobileapps.quidditchplayersandroid.util.RxDispatchers
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.reactive.openSubscription
import timber.log.Timber

class MainViewModel(private val quidditchPlayersService: QuidditchPlayersService, private val rxDispatchers: RxDispatchers) : ViewModel() {
    private lateinit var compositeJob: Job
    private val playersMutableLiveData = MutableLiveData<List<Player>>()
    private val statusMutableLiveData = MutableLiveData<Status>()
    val players: LiveData<List<Player>> = playersMutableLiveData
    val status: LiveData<Status> = statusMutableLiveData
    private val tag = MainViewModel::class.java.simpleName

    //Key = Id & Value = index
    val playersIndexMap = hashMapOf<Int, Int>()

    fun initCoroutineJobs() {
        compositeJob = Job()
        getPlayersAndPositions()
    }

    override fun onCleared() {
        GlobalScope.launch(rxDispatchers.io) {
            quidditchPlayersService.endStatusUpdates()
        }
        compositeJob.cancel()
    }

    private fun getPlayersAndPositions() {
        try {
            GlobalScope.launch(compositeJob + rxDispatchers.io) {
                val playersAsync = quidditchPlayersService.getPlayersAsync()
                val positionsAsync = quidditchPlayersService.getPositionsAsync()

                val players = getUpdatePlayersWithPositionName(playersAsync.await(), positionsAsync.await())

                withContext(rxDispatchers.main) {
                    playersMutableLiveData.value = players
                }

            }
        } catch (e: Exception) {
            Timber.tag(tag).e("getPlayersAndPositions error message: %s", e.toString())
        }
    }

    private fun getUpdatePlayersWithPositionName(players: List<Player>, positions: List<Position>): List<Player> {
        val positionsMap = hashMapOf<Int, String>()
        for (position in positions) {
            positionsMap[position.id] = position.positionName
        }

        for ((index, player) in players.withIndex()) {
            player.positionName = positionsMap[player.position] ?: ""
            player.status = "No Status"
            playersIndexMap[player.id] = index
        }

        return players
    }

    fun listenToStatuses() {
        try {
            GlobalScope.launch(compositeJob + rxDispatchers.io) {
                val channel = getStatuses()

                for (status in channel) {
                    withContext(rxDispatchers.main) {
                        statusMutableLiveData.value = status
                    }
                }
            }
        } catch (e: Exception) {
            Timber.tag(tag).e("listenToStatuses() error message: %s", e.toString())
        }
    }

    private fun getStatuses(): ReceiveChannel<Status> {
        //TODO openSubscription is deprecated use something else
        return quidditchPlayersService.getStatuses().openSubscription()
    }
}
