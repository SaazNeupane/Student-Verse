package com.example.studentverse

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.ui.SinglePostActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class AnswerUIInstrumentedTesting {

    @get:Rule
    val testRule = ActivityScenarioRule(SinglePostActivity::class.java)

    @Test
    fun displayQuestion(){
        ServiceBuilder.token =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwZjU5M2ZkZDY5Y2FjMzY2MDRjNmI5NyIsImlhdCI6MTYyNjg1MTY0MywiZXhwIjoxNjI5NDQzNjQzfQ.gpGsCtGZTovGQ-9nDMzxYI6x-4ITWDcCn9xf2kmoxz8"

        val post = Post("6103b66f0624ab17145fc4a7","I HAVE A QUERY","My Science text is not happening")

        Intent().putExtra("post",post)

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(R.id.btnanswer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}