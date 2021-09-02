package com.example.studentverse

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.ui.DashboardActivity
import org.junit.Rule
import org.junit.Test

class DashboardUIInstrumentedTesting {
    @get:Rule
    val testRule = ActivityScenarioRule(DashboardActivity::class.java)

    @Test
    fun checkquestion() {
        ServiceBuilder.token =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYxMDNjYjVmN2NhMTM1NDlkNGJhMjllOCIsImlhdCI6MTYzMDAzOTI0MiwiZXhwIjoxNjMyNjMxMjQyfQ.1QNMzdP3oWELkTKufuVVQuXu7sDnpjJaRF2wE_munQc"

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.rvques))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}