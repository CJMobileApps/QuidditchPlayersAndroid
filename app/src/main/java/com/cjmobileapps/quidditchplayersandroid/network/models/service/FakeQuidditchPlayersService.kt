package com.cjmobileapps.quidditchplayersandroid.network.models.service

import com.cjmobileapps.quidditchplayersandroid.fakedata.FakeData
import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Position
import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import io.reactivex.Flowable
import io.reactivex.Single

class FakeQuidditchPlayersService : QuidditchPlayersServiceImpl {

    override fun getPlayers(): Single<List<Player>> = Single.just(FakeData.players)

    override fun getPositions(): Single<List<Position>> = Single.just(FakeData.positions)

    override fun getStatuses(): Flowable<Status> = Flowable.just(FakeData.status)

    override fun endStatusUpdates(): Single<Boolean> = Single.just(false)
}
