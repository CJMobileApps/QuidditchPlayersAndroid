package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cjmobileapps.quidditchplayersandroid.network.QuidditchPlayersService
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.MainViewModel

class MainViewModelFactory(private val quidditchPlayersService: QuidditchPlayersService) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(quidditchPlayersService) as T
    }
}
