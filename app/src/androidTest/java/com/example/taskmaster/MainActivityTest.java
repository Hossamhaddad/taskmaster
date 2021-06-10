package com.example.taskmaster;

import static org.junit.Assert.*;
import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Rule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withInputType;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void testUserNameTitle() {
        onView(withId(R.id.viewUserName)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddTaskButton() {
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.newTaskTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.newTaskDescription)).check(matches(isDisplayed()));
    }
    @Test
    public void testAllTasView() {
        onView(withId(R.id.button3)).perform(click());
    }
    @Test
    public void testAddUserName() {
        onView(withId(R.id.addUserName)).perform(click());
    }

}