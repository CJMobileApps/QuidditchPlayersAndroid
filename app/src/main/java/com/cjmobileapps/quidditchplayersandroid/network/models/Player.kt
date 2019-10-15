package com.cjmobileapps.quidditchplayersandroid.network.models

data class Player(
   val firstName: String,
   val lastName: String,
   val favoriteSubject: String,
   val position: Int,
   var positionName: String?,
   val imageUrl: String,
   val yearsPlayed: List<Int>
)
