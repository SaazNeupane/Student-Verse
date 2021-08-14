package com.example.studentverse

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import com.example.studentverse.activity.fragments.SearchFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class SearchInstrumentedTesting {

    @Test
    fun searchQuestion(){
    launchFragmentInContainer<SearchFragment>()
        onView(withId(R.id.etsearch)).check(matches(isDisplayed()))
    }
}