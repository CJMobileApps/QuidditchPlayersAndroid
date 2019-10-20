package com.cjmobileapps.quidditchplayersandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjmobileapps.quidditchplayersandroid.QuidditchPlayersApplication
import com.cjmobileapps.quidditchplayersandroid.R
import com.cjmobileapps.quidditchplayersandroid.databinding.ActivityMainBinding
import com.cjmobileapps.quidditchplayersandroid.ui.dagger.DaggerMainComponent
import com.cjmobileapps.quidditchplayersandroid.ui.dagger.MainModule
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.MainViewModel
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx.Event
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val quidditchPlayersApplication = QuidditchPlayersApplication.get(this)

        DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .quidditchPlayersApplicationComponent(quidditchPlayersApplication.quidditchPlayersApplicationComponent)
                .build()
                .inject(this)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.mainActivityPlayers.layoutManager = LinearLayoutManager(this)

        mainViewModel.players.observe(this, Observer { players ->
            mainAdapter = MainAdapter(players)
            binding.mainActivityPlayers.adapter = mainAdapter
            mainViewModel.processEvent(Event.GetStatusesEvent)
        })
        mainViewModel.status.observe(this, Observer { status ->
            val index = mainViewModel.playersIndexMap[status.id]
            if (index != null) {
                mainAdapter.players[index].status = status.status
                mainAdapter.notifyItemChanged(index)
            }
        })

        mainViewModel.initRx()
        mainViewModel.processEvent(Event.GetPlayersAndPositionsEvent)
    }
}
