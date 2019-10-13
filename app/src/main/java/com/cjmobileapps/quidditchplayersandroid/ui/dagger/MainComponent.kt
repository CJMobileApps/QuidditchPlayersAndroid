package com.cjmobileapps.quidditchplayersandroid.ui.dagger

import com.cjmobileapps.quidditchplayersandroid.dagger.QuidditchPlayersApplicationComponent
import com.cjmobileapps.quidditchplayersandroid.ui.MainActivity
import dagger.Component

@MainScope
@Component(modules = [MainModule::class], dependencies = [QuidditchPlayersApplicationComponent::class])
interface MainComponent {

    fun inject(mainActivity: MainActivity)
}
