package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.rx

sealed class Event {
    object GetPlayersEvent: Event()
    object GetPositionsEvent: Event()
    object GetPlayersAndPositionsEvent: Event()
}
