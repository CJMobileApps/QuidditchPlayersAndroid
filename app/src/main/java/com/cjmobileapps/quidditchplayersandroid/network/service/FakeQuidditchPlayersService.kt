package com.cjmobileapps.quidditchplayersandroid.network.service

import com.cjmobileapps.quidditchplayersandroid.fakedata.FakeData
import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import com.cjmobileapps.quidditchplayersandroid.network.util.ResponseWrapper
import io.reactivex.Flowable
import io.reactivex.Single

class FakeQuidditchPlayersService : QuidditchPlayersServiceImpl {

    override fun getPlayers(): Single<ResponseWrapper<List<Player>>> = Single.just(
            ResponseWrapper(
                    data = FakeData.players,
                    statusCode = 200
            )
    )

    override fun getPositions(): Single<ResponseWrapper<Map<String, String>>> = Single.just(
            ResponseWrapper(
                    data = FakeData.positions,
                    statusCode = 200
            )
    )

    override fun getStatuses(): Flowable<Status> = Flowable.just(FakeData.status)

    override fun endStatusUpdates(): Single<Boolean> = Single.just(false)
}
