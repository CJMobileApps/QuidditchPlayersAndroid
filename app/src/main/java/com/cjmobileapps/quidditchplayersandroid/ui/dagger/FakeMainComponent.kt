package com.cjmobileapps.quidditchplayersandroid.ui.dagger

import com.cjmobileapps.quidditchplayersandroid.dagger.FakeQuidditchPlayersApplicationComponent
import dagger.Component

@MainScope
@Component(modules = [MainModule::class], dependencies = [FakeQuidditchPlayersApplicationComponent::class])
interface FakeMainComponent : MainComponent {

}
