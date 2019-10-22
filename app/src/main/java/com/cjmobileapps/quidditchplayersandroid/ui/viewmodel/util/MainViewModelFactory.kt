package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cjmobileapps.quidditchplayersandroid.network.QuidditchPlayersService
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.MainViewModel
import com.cjmobileapps.quidditchplayersandroid.util.RxDispatchers

class MainViewModelFactory(private val quidditchPlayersService: QuidditchPlayersService, private val rxDispatchers: RxDispatchers) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(quidditchPlayersService, rxDispatchers) as T
    }
}
