package com.cjmobileapps.quidditchplayersandroid.dagger.module

import android.content.Context
import com.cjmobileapps.quidditchplayersandroid.dagger.QuidditchPlayersApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(context: Context) {
    private val context = context.applicationContext

    @QuidditchPlayersApplicationScope
    @Provides
    fun context() : Context {
        return context
    }
}
