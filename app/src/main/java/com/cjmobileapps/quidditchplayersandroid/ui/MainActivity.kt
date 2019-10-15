package com.cjmobileapps.quidditchplayersandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cjmobileapps.quidditchplayersandroid.QuidditchPlayersApplication
import com.cjmobileapps.quidditchplayersandroid.R
import com.cjmobileapps.quidditchplayersandroid.ui.dagger.DaggerMainComponent
import com.cjmobileapps.quidditchplayersandroid.ui.dagger.MainModule
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.MainViewModel
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx.Event
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quidditchPlayersApplication = QuidditchPlayersApplication.get(this)

        DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .quidditchPlayersApplicationComponent(quidditchPlayersApplication.quidditchPlayersApplicationComponent)
                .build()
                .inject(this)

        mainViewModel.initRx()
        mainViewModel.processEvent(Event.GetPlayersAndPositionsEvent)
        /*model.getUsers().observe(this, Observer<List<User>>{ users ->
            // update UI
        }) */

    }
}
