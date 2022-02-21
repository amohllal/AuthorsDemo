package com.assignment.clientapp.presentation.views.ui.frgaments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.assignment.clientapp.R
import com.assignment.clientapp.presentation.views.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test

class AuthorsFragmentTest {

    @get:Rule
    val activityRul = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testAuthorFragment_isVisible_onAppLaunch() {
        onView(withId(R.id.author_rv)).check(matches(isDisplayed()))
    }


}