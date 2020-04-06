package com.cjmobileapps.quidditchplayersandroid.network.models

data class Player(
   val id: Int,
   val firstName: String,
   val lastName: String,
   val favoriteSubject: String,
   val position: Int,
   var positionName: String? = null,
   val imageUrl: String,
   val yearsPlayed: List<Int>
) {
    val fullName: String
        get() = "$firstName $lastName"

    var status: String = ""
}
