package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cjmobileapps.quidditchplayersandroid.network.QuidditchPlayersService
import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx.Event
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx.MainUiStateModel
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx.Result
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx.Result.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel(private val quidditchPlayersService: QuidditchPlayersService) : ViewModel() {
    private lateinit var compositeDisposable: CompositeDisposable
    private val eventsSubject: PublishSubject<Event> = PublishSubject.create()
    private val playersMutableLiveData = MutableLiveData<List<Player>>()
    private val statusMutableLiveData = MutableLiveData<Status>()
    val players: LiveData<List<Player>> = playersMutableLiveData
    val status: LiveData<Status> = statusMutableLiveData

    //Key = Id & Value = index
    val playersIndexMap = hashMapOf<Int, Int>()

    fun initRx() {
        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(compose().subscribe({ mainUiStateModel ->
            subscribeStateModelRender(mainUiStateModel)
        }, { error ->
            //TODO replace logging with timber
            Log.d("HERE_", "error: $error")
        }))
    }

    override fun onCleared() {
        processEvent(Event.EndStatusesEvent)
        compositeDisposable.clear()
    }

    fun processEvent(event: Event) {
        eventsSubject.onNext(event)
    }

    private fun compose(): Observable<MainUiStateModel> {
        return eventsSubject
                .publish { event ->

                    event.ofType(Event.GetPlayersAndPositionsEvent::class.java).publish { getPlayersAndPositionsEvent ->
                        val getPlayers: Observable<GetPlayersResult> = getPlayersAndPositionsEvent.compose(getPlayers)
                        val getPositions: Observable<GetPositionsResult> = getPlayersAndPositionsEvent.compose(getPositions)
                        getPlayersAndPositions(getPlayers, getPositions)
                    }.mergeWith(event.ofType(Event.GetStatusesEvent::class.java).compose(getStatuses))
                            .mergeWith(event.ofType(Event.EndStatusesEvent::class.java).compose(endStatuses))

                }
                .scan(MainUiStateModel.IdleState, resultsToStateUi)
    }

    private val resultsToStateUi = BiFunction { _: MainUiStateModel, result: Result ->

        when (result) {
            is GetPlayersResult ->
                when (result) {
                    is GetPlayersResult.Success -> {
                        MainUiStateModel.GetPlayersMainUiStateModel.inProgress()
                    }
                    is GetPlayersResult.InProgress -> {
                        MainUiStateModel.GetPlayersMainUiStateModel.inProgress()
                    }
                    is GetPlayersResult.Failure -> {
                        MainUiStateModel.GetPlayersMainUiStateModel.failure(result.message, result.statusCode)
                    }
                }
            is GetPositionsResult -> {
                when (result) {
                    is GetPositionsResult.Success -> {
                        MainUiStateModel.GetPlayersMainUiStateModel.inProgress()
                    }
                    is GetPositionsResult.InProgress -> {
                        MainUiStateModel.GetPlayersMainUiStateModel.inProgress()
                    }
                    is GetPositionsResult.Failure -> {
                        MainUiStateModel.GetPlayersMainUiStateModel.failure(result.message, result.statusCode)
                    }
                }
            }
            is GetPlayersAndPositionsResult -> {
                when (result) {
                    is GetPlayersAndPositionsResult.Success ->
                        MainUiStateModel.GetPlayersMainUiStateModel.success(result.players)
                    is GetPlayersAndPositionsResult.Failure ->
                        MainUiStateModel.GetPlayersMainUiStateModel.failure(result.message, result.statusCode)
                    is GetPlayersAndPositionsResult.InProgress ->
                        MainUiStateModel.GetPlayersMainUiStateModel.inProgress()
                }
            }
            is GetStatusesResult -> {
                when (result) {
                    is GetStatusesResult.Success ->
                        MainUiStateModel.GetStatusesMainUiStateModel.success(result.status)
                    is GetStatusesResult.Failure ->
                        MainUiStateModel.GetStatusesMainUiStateModel.failure(result.message, result.statusCode)
                    is GetStatusesResult.InProgress ->
                        MainUiStateModel.GetStatusesMainUiStateModel.inProgress()
                }
            }
            EndStatusesResult -> MainUiStateModel.EndStatusesMainUiStateModel
        }
    }

    private val getPlayers = ObservableTransformer<Event, GetPlayersResult> { event ->
        event.flatMap {
            quidditchPlayersService.getPlayers().toObservable()
                    .map { players -> GetPlayersResult.Success(players) }
                    .cast(GetPlayersResult::class.java)
                    .onErrorReturn { error -> GetPlayersResult.Failure(error) }
                    .subscribeOn(Schedulers.io())
        }
    }

    private val getPositions = ObservableTransformer<Event, GetPositionsResult> { event ->
        event.flatMap {
            quidditchPlayersService.getPositions().toObservable()
                    .map { positions -> GetPositionsResult.Success(positions) }
                    .cast(GetPositionsResult::class.java)
                    .onErrorReturn { error -> GetPositionsResult.Failure(error) }
                    .subscribeOn(Schedulers.io())
        }
    }

    private val getStatuses = ObservableTransformer<Event, GetStatusesResult> { event ->
        event.flatMap {
            quidditchPlayersService.getStatuses().toObservable()
                    .map { positions -> GetStatusesResult.Success(positions) }
                    .cast(GetStatusesResult::class.java)
                    .onErrorReturn { error -> GetStatusesResult.Failure(error) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(GetStatusesResult.InProgress)
        }
    }

    private val endStatuses = ObservableTransformer<Event, EndStatusesResult> { event ->
        event.flatMap {
            quidditchPlayersService.endStatusUpdates().toObservable()
                    .map { _ -> EndStatusesResult }
                    .cast(EndStatusesResult::class.java)
                    .onErrorReturn { _ -> EndStatusesResult }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    private fun getPlayersAndPositions(
            playersObservable: Observable<GetPlayersResult>,
            positionsObservable: Observable<GetPositionsResult>
    ): Observable<Result> {
        return Observable.zip(
                playersObservable.onErrorReturn { error -> GetPlayersResult.Failure(error) },
                positionsObservable.onErrorReturn { error -> GetPositionsResult.Failure(error) },
                BiFunction<GetPlayersResult, GetPositionsResult, Result> { playersResult, positionsResult ->

                    val players = (playersResult as GetPlayersResult.Success).players
                    val positions = (positionsResult as GetPositionsResult.Success).positions

                    val positionsMap = hashMapOf<Int, String>()
                    for (position in positions) {
                        positionsMap[position.id] = position.positionName
                    }

                    for ((index, player) in players.withIndex()) {
                        player.positionName = positionsMap[player.position] ?: ""
                        player.status = "No Status"
                        playersIndexMap[player.id] = index
                    }

                    GetPlayersAndPositionsResult.Success(players)
                }
        )
                .onErrorReturn { error -> GetPlayersAndPositionsResult.Failure(error) }
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(GetPlayersAndPositionsResult.InProgress)
    }

    private fun subscribeStateModelRender(mainUiStateModel: MainUiStateModel) {

        if (mainUiStateModel is MainUiStateModel.GetPlayersMainUiStateModel) {
            if (mainUiStateModel.inProgress) {
                //TODO add timber logger
            } else {

                if (mainUiStateModel.success) {
                    playersMutableLiveData.value = mainUiStateModel.playerList
                } else {
                    Log.d("HERE_", "error message: " + mainUiStateModel.errorMessage)
                    Log.d("HERE_", "error status: " + mainUiStateModel.statusCode)
                }

            }
        } else if (mainUiStateModel is MainUiStateModel.GetStatusesMainUiStateModel) {
            if (mainUiStateModel.inProgress) {
                //TODO add timber logger
            } else {

                if (mainUiStateModel.success) {
                    statusMutableLiveData.value = mainUiStateModel.status
                } else {
                    Log.d("HERE_", "error message: " + mainUiStateModel.errorMessage)
                    Log.d("HERE_", "error status: " + mainUiStateModel.statusCode)
                }
            }
        } else if (mainUiStateModel is MainUiStateModel.EndStatusesMainUiStateModel) {
            Log.d("HERE_", "EndStatuses")
        }
    }
}
