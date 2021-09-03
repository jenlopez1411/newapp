package com.sophia.jlo.newapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import kotlin.jvm.JvmField;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest{
    @JvmField
    @Rule
    public ActivityTestRule<MainActivity> intentsTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testIntent()
    {
        onView(withId(R.id.userNameEditText))
                .perform(typeText("csumb1"));

        onView(withId(R.id.passwordEditText))
                .perform(typeText("otter1"));

        onView(withId(R.id.passwordEditText)).perform(closeSoftKeyboard());

        onView(withId(R.id.loginBtn))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withId(R.id.textView2))
                .check(matches(isDisplayed()));


    }


}