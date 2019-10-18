package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cjmobileapps.quidditchplayersandroid.network.QuidditchPlayersService
import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx.Event
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx.MainUiStateModel
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx.Result
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx.Result.GetPlayersResult
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx.Result.GetPositionsResult
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
    val players: LiveData<List<Player>> = playersMutableLiveData

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
                            }

                }
                .scan(MainUiStateModel.idle(), resultsToStateUi)
    }

    private val resultsToStateUi = BiFunction { _: MainUiStateModel, result: Result ->

        when (result) {
            is GetPlayersResult ->
                when (result) {
                    is GetPlayersResult.Success -> {
                        MainUiStateModel.inProgress()
                    }
                    is GetPlayersResult.InProgress -> {
                        MainUiStateModel.inProgress()
                    }
                    is GetPlayersResult.Failure -> {
                        MainUiStateModel.failure(result.message, result.statusCode)
                    }
                }
            is GetPositionsResult -> {
                when (result) {
                    is GetPositionsResult.Success -> {
                        MainUiStateModel.inProgress()
                    }
                    is GetPositionsResult.InProgress -> {
                        MainUiStateModel.inProgress()
                    }
                    is GetPositionsResult.Failure -> {
                        MainUiStateModel.failure(result.message, result.statusCode)
                    }
                }
            }
            is Result.GetPlayersAndPositionsResult -> {
                when (result) {
                    is Result.GetPlayersAndPositionsResult.Success ->
                        MainUiStateModel.success(result.players)
                    is Result.GetPlayersAndPositionsResult.Failure ->
                        MainUiStateModel.failure(result.message, result.statusCode)
                    is Result.GetPlayersAndPositionsResult.InProgress ->
                        MainUiStateModel.inProgress()
                }
            }
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


                    for(player in players) {
                        player.positionName = positionsMap[player.position] ?: ""
                    }


                    Result.GetPlayersAndPositionsResult.Success(players)
                }
        )
                .onErrorReturn { error -> Result.GetPlayersAndPositionsResult.Failure(error) }
                .observeOn(AndroidSchedulers.mainThread())
                .startWith(Result.GetPlayersAndPositionsResult.InProgress)
    }


    private fun subscribeStateModelRender(mainUiStateModel: MainUiStateModel) {

        if (mainUiStateModel.inProgress) {

        } else {

            if (mainUiStateModel.success) {
                playersMutableLiveData.value = mainUiStateModel.playerList
            } else {
                Log.d("HERE_", "error message: " + mainUiStateModel.errorMessage)
                Log.d("HERE_", "error status: " + mainUiStateModel.statusCode)
            }

        }
    }
}
