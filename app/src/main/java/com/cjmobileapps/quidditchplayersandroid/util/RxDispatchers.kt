package com.cjmobileapps.quidditchplayersandroid.util

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.rx2.asCoroutineDispatcher
import java.util.concurrent.Executors

class RxDispatchers {
    val io = Schedulers.io().asCoroutineDispatcher()
    val computation = Schedulers.computation().asCoroutineDispatcher()
    val main = AndroidSchedulers.mainThread().asCoroutineDispatcher()
    val db = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
}
