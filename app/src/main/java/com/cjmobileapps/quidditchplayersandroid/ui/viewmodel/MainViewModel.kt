package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import com.cjmobileapps.quidditchplayersandroid.network.service.QuidditchPlayersServiceImpl
import com.cjmobileapps.quidditchplayersandroid.util.toErrorWrapper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.Exception

class MainViewModel(private val quidditchPlayersService: QuidditchPlayersServiceImpl) : ViewModel() {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val playersMutableLiveData = MutableLiveData<List<Player>>()
    private val statusMutableLiveData = MutableLiveData<Status>()
    val players: LiveData<List<Player>> = playersMutableLiveData
    val status: LiveData<Status> = statusMutableLiveData
    private val tag = MainViewModel::class.java.simpleName

    //Key = Id & Value = index
    val playersIndexMap = hashMapOf<String, Int>()

    override fun onCleared() {
        compositeDisposable.add(endStatuses())
        compositeDisposable.clear()
    }

    fun getPlayers() {
        compositeDisposable.add(getPlayersAndPositions(getPlayersObservable(), getPositionsObservable()))
        compositeDisposable.add(getStatuses())
    }

    private fun getPlayersObservable(): Observable<List<Player>> {
        return quidditchPlayersService.getPlayers()
                .map { response ->
                    response.data ?: throw Exception("Blah should be a custom exception")
                }
                .toObservable()
                .subscribeOn(Schedulers.io())
    }

    private fun getPositionsObservable(): Observable<Map<String, String>> {
        return quidditchPlayersService.getPositions()
                .toObservable()
                .map { response ->
                    response.data ?: throw Exception("Blah should be a custom exception")
                }
                .subscribeOn(Schedulers.io())
    }

    private fun getPlayersAndPositions(
            playersObservable: Observable<List<Player>>,
            positionsObservable: Observable<Map<String, String>>
    ): Disposable {
        return Observable.zip(playersObservable, positionsObservable, BiFunction<List<Player>, Map<String, String>, List<Player>> { players, positions ->

            for ((index, player) in players.withIndex()) {
                player.positionName = positions[player.position.toString()] ?: ""
                player.status = "No Status"
                playersIndexMap[player.id] = index
            }

            players
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ players ->
                    playersMutableLiveData.value = players
                }, { error ->
                    Timber.tag(tag).e("GetPlayersAndPositions call error message: %s", error.toErrorWrapper().message)
                })
    }

    private fun getStatuses(): Disposable {
        return quidditchPlayersService.getStatuses()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ status ->
                    statusMutableLiveData.value = status
                }, { error ->
                    Timber.tag(tag).e("GetStatuses call error message: %s", error.toErrorWrapper().message)
                })
    }

    private fun endStatuses(): Disposable {
        return quidditchPlayersService.endStatusUpdates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                }, { error ->
                    Timber.tag(tag).e("EndStatuses call error message: %s", error.toErrorWrapper().message)
                })
    }
}
