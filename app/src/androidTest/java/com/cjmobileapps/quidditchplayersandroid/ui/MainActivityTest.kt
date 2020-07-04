package com.cjmobileapps.quidditchplayersandroid.ui

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.cjmobileapps.atPosition
import com.cjmobileapps.quidditchplayersandroid.R
import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    var rule = ActivityScenarioRule(MainActivity::class.java)

    //TODO add Espresso Idling Resource

    //TODO add EspressoHelper to a base testing class
    object EspressoHelper {

        //Cast the activity to use it
        fun getCurrentActivity(): Activity? {
            var currentActivity: Activity? = null
            getInstrumentation().runOnMainSync { run { currentActivity = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED).elementAtOrNull(0) } }
            return currentActivity
        }
    }

    @Test
    fun activityShouldBeInViewTest() {

        onView(withId(R.id.mainActivity_container)).check(matches(isDisplayed()))
    }

    @Test
    fun listShouldBeDisplayed() {

        onView(withId(R.id.mainActivity_players)).check(matches(isDisplayed()))

    }

    @Test
    fun firstItemShouldBeDisplayed() {

        onView(withId(R.id.mainActivity_players))
                .check(matches(atPosition(0, hasDescendant(withText(mockPlayers[0].fullName)))))

        onView(withId(R.id.mainActivity_players))
                .check(matches(atPosition(0, hasDescendant(withText("Seeker")))))

        onView(withId(R.id.mainActivity_players))
                .check(matches(atPosition(0, hasDescendant(withText(mockPlayers[0].favoriteSubject)))))

        onView(withId(R.id.mainActivity_players))
                .check(matches(atPosition(0, hasDescendant(withText(mockPlayers[0].yearsPlayed.toString())))))
    }

    @Test
    fun lastItemShouldBeDisplayed() {


        onView(withId(R.id.mainActivity_players))
                .perform(scrollToPosition<MainAdapter.MainAdapterHolder>(6))
                .check(matches(atPosition(6, hasDescendant(withText(mockPlayers[6].fullName)))))

        onView(withId(R.id.mainActivity_players))
                .check(matches(atPosition(6, hasDescendant(withText("Keeper")))))

        onView(withId(R.id.mainActivity_players))
                .check(matches(atPosition(6, hasDescendant(withText(mockPlayers[6].favoriteSubject)))))

        onView(withId(R.id.mainActivity_players))
                .check(matches(atPosition(6, hasDescendant(withText(mockPlayers[6].yearsPlayed.toString())))))
    }

    companion object {
        val mockPlayers = listOf(
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

    }
}
