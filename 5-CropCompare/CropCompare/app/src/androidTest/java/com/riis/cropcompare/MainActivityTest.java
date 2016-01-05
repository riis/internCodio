package com.riis.cropcompare;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.riis.cropcompare.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mMainActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testClickCropButton()
    {
        //Tests the state spinner is displayed
        onView(withId(R.id.stateSpinner)).check(matches(isDisplayed()));
        //Tests clicking on Michigan in the state spinner selects it
        onView(withId(R.id.stateSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Michigan"))).perform(click());
        onView(withId(R.id.stateSpinner)).check(matches(withSpinnerText(containsString("Michigan"))));
        //Enter Text in the acreage
        onView(withId(R.id.acreage)).perform(typeText("2"));
        //Close soft keyboard
        closeSoftKeyboard();
        //Click the next button and tranistion to next screen
        onView(withId(R.id.fab_button)).perform(click());
        onView(withId(R.id.crop_container))
                .check(matches(isDescendantOfA(withId(R.id.crop_scroll_view))));
    }
}
