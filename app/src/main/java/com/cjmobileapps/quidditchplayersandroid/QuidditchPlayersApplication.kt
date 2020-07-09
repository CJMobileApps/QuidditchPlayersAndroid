package com.cjmobileapps.quidditchplayersandroid

import android.app.Activity
import android.app.Application
import android.content.Context
import com.cjmobileapps.quidditchplayersandroid.dagger.DaggerFakeQuidditchPlayersApplicationComponent
import com.cjmobileapps.quidditchplayersandroid.dagger.DaggerQuidditchPlayersApplicationComponent
import com.cjmobileapps.quidditchplayersandroid.dagger.FakeQuidditchPlayersApplicationComponent
import com.cjmobileapps.quidditchplayersandroid.dagger.QuidditchPlayersApplicationComponent
import com.cjmobileapps.quidditchplayersandroid.dagger.module.ContextModule
import timber.log.Timber

class QuidditchPlayersApplication : Application() {

    lateinit var quidditchPlayersApplicationComponent: QuidditchPlayersApplicationComponent

    lateinit var fakeQuidditchPlayersApplicationComponent: FakeQuidditchPlayersApplicationComponent


    companion object {

        fun get(activity: Activity): QuidditchPlayersApplication {
            return activity.application as QuidditchPlayersApplication
        }

        fun get(context: Context): QuidditchPlayersApplication {
            return context.applicationContext as QuidditchPlayersApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        quidditchPlayersApplicationComponent = DaggerQuidditchPlayersApplicationComponent.builder()
                .contextModule(ContextModule(this)).build()

        quidditchPlayersApplicationComponent.injectQuidditchPlayersApplicationComponent(this)

        fakeQuidditchPlayersApplicationComponent = DaggerFakeQuidditchPlayersApplicationComponent.builder()
                .contextModule(ContextModule(this)).build()

        fakeQuidditchPlayersApplicationComponent.injectFakeQuidditchPlayersApplicationComponent(this)


        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
