package com.cjmobileapps.quidditchplayersandroid.dagger

import android.content.Context
import com.cjmobileapps.quidditchplayersandroid.QuidditchPlayersApplication
import com.cjmobileapps.quidditchplayersandroid.dagger.module.ContextModule
import com.cjmobileapps.quidditchplayersandroid.dagger.module.NetworkModule
import com.cjmobileapps.quidditchplayersandroid.network.QuidditchPlayersService
import dagger.Component

@QuidditchPlayersApplicationScope
@Component(modules = [ContextModule::class, NetworkModule::class])
interface QuidditchPlayersApplicationComponent  {

    fun provideContext(): Context

    fun provideQuidditchPlayersService(): QuidditchPlayersService

    fun injectQuidditchPlayersApplicationComponent(quidditchPlayersApplication: QuidditchPlayersApplication)
}
