package com.riis.cropcompare;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.riis.cropcompare.activity.CropSelectActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class CropSelectActivityTest
{
    @Rule
    public ActivityTestRule<CropSelectActivity> mCropSelectActivityRule =
            new ActivityTestRule<>(CropSelectActivity.class, false, false);

    @Before
    public void setUp()
    {
        Intent testIntent = new Intent();
        testIntent.putExtra("state", "Michigan");
        testIntent.putExtra("acreage", "2");
        mCropSelectActivityRule.launchActivity(testIntent);
    }

    @Test
    public void testClickCropButton()
    {
        onView(withText("CORN")).check(matches(isDescendantOfA(withId(R.id.crop_container))));
        onView(withText("CORN")).perform(click());
    }
}
