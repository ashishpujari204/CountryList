package com.ashish.country

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ashish.country.ui.countrylist.CountryList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class CountryListInstrumentedTest {

    @get:Rule
    var mCurrentListActivityRule: ActivityScenarioRule<CountryList> =
        ActivityScenarioRule(CountryList::class.java)

    @Test
    fun onLaunchCheckListIsDisplayed() {
        ActivityScenario.launch(CountryList::class.java)
        onView(withId(R.id.countryList)).check(matches(isDisplayed()))
    }

    @Test
    fun itemClick() {
        ActivityScenario.launch(CountryList::class.java)
        Thread.sleep(3000)
        onView(withId(R.id.countryList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
    }
}
