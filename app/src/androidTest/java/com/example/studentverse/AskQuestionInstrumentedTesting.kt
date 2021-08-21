package com.example.studentverse

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.ui.AskQuestionActivity
import com.example.studentverse.activity.ui.SignupActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@LargeTest
@RunWith(JUnit4::class)
class AskQuestionInstrumentedTesting {

    @get:Rule
    val testRule = ActivityScenarioRule(AskQuestionActivity::class.java)

    @Test
    fun checkaskquestion() {

        ServiceBuilder.token =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYxMDNjYjVmN2NhMTM1NDlkNGJhMjllOCIsImlhdCI6MTYyNzYzODg2MCwiZXhwIjoxNjMwMjMwODYwfQ.gR3xBHBCI4a1Banna8IlgfkS1cU1tzYHXGcD3WoUksc"

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.ettitle))
            .perform(ViewActions.typeText("What is Nested ScrollView?"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.etbody))
            .perform(ViewActions.typeText("I heard about it when i was doing my android project and is a bit confused."))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.ettags))
            .perform(ViewActions.typeText("Android, Kotlin"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnpost))
            .perform(ViewActions.click())

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.bottomnavigation))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}