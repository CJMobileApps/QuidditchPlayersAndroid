package com.cjmobileapps.quidditchplayersandroid.dagger.module

import com.cjmobileapps.quidditchplayersandroid.dagger.QuidditchPlayersApplicationScope
import com.cjmobileapps.quidditchplayersandroid.util.RxDispatchers
import dagger.Module
import dagger.Provides

@Module
class CoroutineModule {

    @QuidditchPlayersApplicationScope
    @Provides
    fun rxDispatcher(): RxDispatchers {
        return RxDispatchers()
    }
}
