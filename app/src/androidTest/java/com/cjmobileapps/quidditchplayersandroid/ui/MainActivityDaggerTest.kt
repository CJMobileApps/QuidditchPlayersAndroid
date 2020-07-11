package com.cjmobileapps.quidditchplayersandroid.ui

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.cjmobileapps.quidditchplayersandroid.BaseEspressoTest
import com.cjmobileapps.quidditchplayersandroid.R
import com.cjmobileapps.quidditchplayersandroid.dagger.util.DaggerConstants
import com.cjmobileapps.quidditchplayersandroid.fakedata.FakeData
import com.cjmobileapps.quidditchplayersandroid.ui.viewmodel.MainViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


//If dagger doesn't build use  ./gradlew assembleAndroidTest

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityDaggerTest : BaseEspressoTest() {

    private val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
            .putExtra(DaggerConstants.SHOULD_USE_DAGGER, true)

    @get:Rule
    var scenarioRule = ActivityScenarioRule<MainActivity>(intent)

    @Inject
    lateinit var mainViewModel: MainViewModel

    private val mockPlayers = FakeData.players


    @Test
    fun activityShouldBeInViewTest() {

        onView(ViewMatchers.withId(R.id.mainActivity_container)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun listShouldBeDisplayed() {

        onView(ViewMatchers.withId(R.id.mainActivity_players)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun firstItemShouldBeDisplayed() {

        onView(ViewMatchers.withId(R.id.mainActivity_players))
                .check(ViewAssertions.matches(atPosition(0, ViewMatchers.hasDescendant(withText(mockPlayers[0].fullName)))))

        onView(ViewMatchers.withId(R.id.mainActivity_players))
                .check(ViewAssertions.matches(atPosition(0, ViewMatchers.hasDescendant(
                        withText(
                                FakeData.positions[(mockPlayers[0].position).toString()]
                        )
                ))))

        onView(ViewMatchers.withId(R.id.mainActivity_players))
                .check(ViewAssertions.matches(atPosition(0, ViewMatchers.hasDescendant(withText(mockPlayers[0].favoriteSubject)))))

        onView(ViewMatchers.withId(R.id.mainActivity_players))
                .check(ViewAssertions.matches(atPosition(0, ViewMatchers.hasDescendant(withText(mockPlayers[0].yearsPlayed.toString())))))

    }

    @Test
    fun lastItemShouldBeDisplayed() {


        onView(ViewMatchers.withId(R.id.mainActivity_players))
                .perform(RecyclerViewActions.scrollToPosition<MainAdapter.MainAdapterHolder>(6))
                .check(ViewAssertions.matches(atPosition(6, ViewMatchers.hasDescendant(withText(mockPlayers[6].fullName)))))

        onView(ViewMatchers.withId(R.id.mainActivity_players))
                .check(ViewAssertions.matches(atPosition(6, ViewMatchers.hasDescendant(
                        withText(FakeData.positions[(mockPlayers[6].position).toString()])
                ))))

        onView(ViewMatchers.withId(R.id.mainActivity_players))
                .check(ViewAssertions.matches(atPosition(6, ViewMatchers.hasDescendant(withText(mockPlayers[6].favoriteSubject)))))

        onView(ViewMatchers.withId(R.id.mainActivity_players))
                .check(ViewAssertions.matches(atPosition(6, ViewMatchers.hasDescendant(withText(mockPlayers[6].yearsPlayed.toString())))))

        onView(ViewMatchers.withId(R.id.mainActivity_players))
                .check(ViewAssertions.matches(atPosition(6, ViewMatchers.hasDescendant(
                        withText(FakeData.status.status)
                ))))
    }
}
