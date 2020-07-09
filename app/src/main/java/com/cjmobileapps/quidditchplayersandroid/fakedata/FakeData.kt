package com.cjmobileapps.quidditchplayersandroid.fakedata

import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Position
import com.cjmobileapps.quidditchplayersandroid.network.models.Status

object FakeData {
    val players = listOf(
            Player(
                    id = 3,
                    firstName = "Harry",
                    lastName = "Potter",
                    yearsPlayed = listOf(1991, 1992, 1993, 1994, 1995, 1996, 1997),
                    favoriteSubject = "Defense Against The Dark Arts",
                    position = 4,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/harry+potter.jpg"
            ),
            Player(
                    id = 4,
                    firstName = "Katie",
                    lastName = "Bell",
                    yearsPlayed = listOf(1991, 1992, 1993, 1994, 1995, 1996, 1997),
                    favoriteSubject = "Transfiguration",
                    position = 1,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/katie+bell.jpg"
            ),
            Player(
                    id = 5,
                    firstName = "Angelina",
                    lastName = "Johnson",
                    yearsPlayed = listOf(1990, 1991, 1993, 1994, 1995, 1996),
                    favoriteSubject = "Care of Magical Creatures",
                    position = 1,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/angelina+johnson.jpg"
            ),
            Player(
                    id = 6,
                    firstName = "Fred",
                    lastName = "Weasley",
                    yearsPlayed = listOf(1990, 1991, 1992, 1993, 1994, 1995, 1996),
                    favoriteSubject = "Charms",
                    position = 2,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/fred+weasley.jpg"
            ),
            Player(
                    id = 7,
                    firstName = "George",
                    lastName = "Weasley",
                    yearsPlayed = listOf(1990, 1991, 1992, 1993, 1994, 1995, 1996),
                    favoriteSubject = "Charms",
                    position = 2,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/george+weasley.jpg"
            ),
            Player(
                    id = 8,
                    firstName = "Alicia",
                    lastName = "Spinnet",
                    yearsPlayed = listOf(1990, 1991, 1992, 1993, 1994, 1995, 1996),
                    favoriteSubject = "Charms",
                    position = 1,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/alicia+spinnet.jpg"
            ),
            Player(
                    id = 9,
                    firstName = "Oliver",
                    lastName = "Wood",
                    yearsPlayed = listOf(1989, 1990, 1991, 1992, 1993, 1994),
                    favoriteSubject = "Potions",
                    position = 3,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/oliver+wood.jpg"
            )
    )
    val positions = listOf(
            Position(
                    id = 1,
                    positionName = "Chaser"
            ),
            Position(
                    id = 2,
                    positionName = "Beater"
            ),
            Position(
                    id = 3,
                    positionName = "Keeper"
            ),
            Position(
                    id = 4,
                    positionName = "Seeker"
            )
    )
    val status = Status(
            id = 2,
            status = "Alicia Spinnet is dueling a Slytherin 🐍"
    )

}
