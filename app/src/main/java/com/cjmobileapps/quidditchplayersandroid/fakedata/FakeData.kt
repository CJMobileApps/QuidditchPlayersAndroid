package com.cjmobileapps.quidditchplayersandroid.fakedata

import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import java.util.*

object FakeData {
    val players = listOf(
            Player(
                    id = UUID.randomUUID().toString(),
                    firstName = "Harry",
                    lastName = "Potter",
                    yearsPlayed = listOf(1991, 1992, 1993, 1994, 1995, 1996, 1997),
                    favoriteSubject = "Defense Against The Dark Arts",
                    position = 4,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/harry+potter.jpg"
            ),
            Player(
                    id = UUID.randomUUID().toString(),
                    firstName = "Katie",
                    lastName = "Bell",
                    yearsPlayed = listOf(1991, 1992, 1993, 1994, 1995, 1996, 1997),
                    favoriteSubject = "Transfiguration",
                    position = 1,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/katie+bell.jpg"
            ),
            Player(
                    id = UUID.randomUUID().toString(),
                    firstName = "Angelina",
                    lastName = "Johnson",
                    yearsPlayed = listOf(1990, 1991, 1993, 1994, 1995, 1996),
                    favoriteSubject = "Care of Magical Creatures",
                    position = 1,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/angelina+johnson.jpg"
            ),
            Player(
                    id = UUID.randomUUID().toString(),
                    firstName = "Fred",
                    lastName = "Weasley",
                    yearsPlayed = listOf(1990, 1991, 1992, 1993, 1994, 1995, 1996),
                    favoriteSubject = "Charms",
                    position = 2,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/fred+weasley.jpg"
            ),
            Player(
                    id = UUID.randomUUID().toString(),
                    firstName = "George",
                    lastName = "Weasley",
                    yearsPlayed = listOf(1990, 1991, 1992, 1993, 1994, 1995, 1996),
                    favoriteSubject = "Charms",
                    position = 2,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/george+weasley.jpg"
            ),
            Player(
                    id = UUID.randomUUID().toString(),
                    firstName = "Alicia",
                    lastName = "Spinnet",
                    yearsPlayed = listOf(1990, 1991, 1992, 1993, 1994, 1995, 1996),
                    favoriteSubject = "Charms",
                    position = 1,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/alicia+spinnet.jpg"
            ),
            Player(
                    id = UUID.randomUUID().toString(),
                    firstName = "Oliver",
                    lastName = "Wood",
                    yearsPlayed = listOf(1989, 1990, 1991, 1992, 1993, 1994),
                    favoriteSubject = "Potions",
                    position = 3,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/oliver+wood.jpg"
            )
    )
    val positions = mapOf(
            "1" to "Chaser",
            "2" to "Beater",
            "3" to "keeper",
            "4" to "Seeker"
    )

    val status = Status(
            id = players[6].id,
            status = "Alicia Spinnet is dueling a Slytherin 🐍"
    )

}
