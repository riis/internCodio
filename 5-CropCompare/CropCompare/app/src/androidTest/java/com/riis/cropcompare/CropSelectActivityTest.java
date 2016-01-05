package com.riis.cropcompare;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.riis.cropcompare.activity.CropSelectActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class CropSelectActivityTest
{
    @Rule
    public ActivityTestRule<CropSelectActivity> mCropSelectActivityRule = new ActivityTestRule<>(CropSelectActivity.class);

    @Before
    public void setUp()
    {
        Intent testIntent = new Intent();
        testIntent.putExtra("state", "Michigan");
        testIntent.putExtra("acreage", "2");
        mCropSelectActivityRule.getActivity().setIntent(testIntent);
    }

    @Test
    public void ButtonListTest()
    {
    }
}
