package com.cjmobileapps.quidditchplayersandroid.ui.dagger

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.cjmobileapps.quidditchplayersandroid.network.service.QuidditchPlayersServiceImpl
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.MainViewModel
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.util.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: AppCompatActivity) {

    @MainScope
    @Provides
    fun mainViewModel(quidditchPlayersService: QuidditchPlayersServiceImpl): MainViewModel {
        return ViewModelProviders.of(activity, MainViewModelFactory(quidditchPlayersService))[MainViewModel::class.java]
    }
}
