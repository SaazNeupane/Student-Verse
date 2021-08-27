package com.example.studentverse

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.fragment.SearchFragment
import com.example.studentverse.activity.fragment.StudyMaterialFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4::class)
class StudyFragmentInstrumentedTesting {

    private lateinit var scenario: FragmentScenario<StudyMaterialFragment>

    @Before
    fun setUp(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_StudentVerse)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun searchTest(){

        ServiceBuilder.token =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYxMDNjYjVmN2NhMTM1NDlkNGJhMjllOCIsImlhdCI6MTYzMDAzOTI0MiwiZXhwIjoxNjMyNjMxMjQyfQ.1QNMzdP3oWELkTKufuVVQuXu7sDnpjJaRF2wE_munQc"

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.rvsubjects))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}