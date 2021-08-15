package com.example.studentverse

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.fragments.SearchFragment
import com.example.studentverse.activity.ui.DashboardActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class SearchInstrumentedTesting {

    private lateinit var scenario: FragmentScenario<SearchFragment>

    @Before
    fun setUp(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_StudentVerse)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun searchTest(){
        onView(withId(R.id.etsearch)).perform(typeText("Java"))

        Espresso.closeSoftKeyboard()

        onView(withId(R.id.btnsearch))
            .perform(ViewActions.click())

        Thread.sleep(2000)

        onView(withId(R.id.rvsearch))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}