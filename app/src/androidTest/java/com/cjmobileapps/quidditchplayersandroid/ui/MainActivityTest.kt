package com.cjmobileapps.quidditchplayersandroid.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.cjmobileapps.quidditchplayersandroid.BaseEspressoTest
import com.cjmobileapps.quidditchplayersandroid.R
import com.cjmobileapps.quidditchplayersandroid.fakedata.FakeData
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest : BaseEspressoTest() {

    @get:Rule
    var rule = ActivityScenarioRule(MainActivity::class.java)

    private val mockPlayers = FakeData.players

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
                .check(matches(atPosition(0, hasDescendant(
                        withText(
                                FakeData.positions[(mockPlayers[0].position).toString()]
                        )
                ))))

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
                .check(matches(atPosition(6, hasDescendant(
                        withText(
                                FakeData.positions[(mockPlayers[6].position).toString()]
                        )
                ))))

        onView(withId(R.id.mainActivity_players))
                .check(matches(atPosition(6, hasDescendant(withText(mockPlayers[6].favoriteSubject)))))

        onView(withId(R.id.mainActivity_players))
                .check(matches(atPosition(6, hasDescendant(withText(mockPlayers[6].yearsPlayed.toString())))))
    }
}
