package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cjmobileapps.quidditchplayersandroid.network.models.service.QuidditchPlayersService
import com.cjmobileapps.quidditchplayersandroid.network.models.service.QuidditchPlayersServiceImpl
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.MainViewModel

class MainViewModelFactory(private val quidditchPlayersService: QuidditchPlayersServiceImpl) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(quidditchPlayersService) as T
    }
}
