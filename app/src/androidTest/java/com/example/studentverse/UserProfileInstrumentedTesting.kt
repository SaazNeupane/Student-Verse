package com.example.studentverse

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.fragment.SearchFragment
import com.example.studentverse.activity.fragment.UserFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class UserProfileInstrumentedTesting {

    private lateinit var scenario: FragmentScenario<UserFragment>

    @Before
    fun setUp(){
        ServiceBuilder.token =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYxMDNjYjVmN2NhMTM1NDlkNGJhMjllOCIsImlhdCI6MTYzMDAzOTI0MiwiZXhwIjoxNjMyNjMxMjQyfQ.1QNMzdP3oWELkTKufuVVQuXu7sDnpjJaRF2wE_munQc"
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_StudentVerse)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun searchTest(){
        Espresso.onView(ViewMatchers.withId(R.id.name))
            .check(ViewAssertions.matches(ViewMatchers.withText("Welcome, saazneu789")))

    }
}