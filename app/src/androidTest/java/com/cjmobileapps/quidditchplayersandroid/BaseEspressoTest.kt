package com.cjmobileapps.quidditchplayersandroid

import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

abstract class BaseEspressoTest {


    fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

//if recycler view helper not good enough checkout https://stackoverflow.com/questions/52737309/espresso-check-recyclerview-items-are-ordered-correctly


    //TODO add Espresso Idling Resource

    //TODO add generics and make a part lazy
    object EspressoHelper {

//    val application: QuidditchPlayersApplication = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as QuidditchPlayersApplication
//        val activity = EspressoHelper.getCurrentActivity() ?: return
//        val activityContext = activity.baseContext ?: return

//        //Cast the activity to use it
//        fun getCurrentActivity(): Activity? {
//            var currentActivity: Activity? = null
//            InstrumentationRegistry.getInstrumentation().runOnMainSync { run { currentActivity = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED).elementAtOrNull(0) } }
//            return currentActivity
    }
}
