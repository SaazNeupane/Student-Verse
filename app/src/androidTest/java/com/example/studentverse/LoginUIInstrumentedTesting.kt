package com.example.studentverse

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.studentverse.activity.ui.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class LoginUIInstrumentedTesting {
    @get:Rule
    val testRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun checkLogin(){
        Espresso.onView(withId(R.id.etlusername))
            .perform(ViewActions.typeText("saazneu789"))

        Espresso.onView(withId(R.id.etlpassword))
            .perform(ViewActions.typeText("12345"))

        Espresso.closeSoftKeyboard()

        Espresso.onView(withId(R.id.btnlogin))
            .perform(ViewActions.click())

        Thread.sleep(2000)

        Espresso.onView(withId(R.id.bottomnavigation))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}