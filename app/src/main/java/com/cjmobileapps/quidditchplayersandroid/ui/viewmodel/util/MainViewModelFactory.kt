package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cjmobileapps.quidditchplayersandroid.network.service.QuidditchPlayersServiceImpl
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.MainViewModel

class MainViewModelFactory(private val quidditchPlayersService: QuidditchPlayersServiceImpl) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(quidditchPlayersService) as T
    }
}
