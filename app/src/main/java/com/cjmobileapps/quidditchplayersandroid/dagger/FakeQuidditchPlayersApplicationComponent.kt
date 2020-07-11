package com.cjmobileapps.quidditchplayersandroid.dagger

import android.content.Context
import com.cjmobileapps.quidditchplayersandroid.QuidditchPlayersApplication
import com.cjmobileapps.quidditchplayersandroid.dagger.module.ContextModule
import com.cjmobileapps.quidditchplayersandroid.dagger.module.FakeNetworkModule
import com.cjmobileapps.quidditchplayersandroid.network.service.QuidditchPlayersServiceImpl
import dagger.Component

@QuidditchPlayersApplicationScope
@Component(modules = [ContextModule::class, FakeNetworkModule::class])
interface FakeQuidditchPlayersApplicationComponent {

    fun provideContext(): Context

    fun provideQuidditchPlayersService(): QuidditchPlayersServiceImpl

    fun injectFakeQuidditchPlayersApplicationComponent(quidditchPlayersApplication: QuidditchPlayersApplication)
}
