package com.riis.cropcompare;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.riis.cropcompare.activity.ResultsActivity;

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
public class ResultsActivityTest
{
    @Rule
    public ActivityTestRule<ResultsActivity> mResultsActivityRule = new ActivityTestRule<>(ResultsActivity.class);

    @Before
    public void setUp()
    {
        Intent testIntent = new Intent();
        testIntent.putExtra("state", "Michigan");
        testIntent.putExtra("acreage", "2");
        testIntent.putExtra("cropSelected", "CORN");
        mResultsActivityRule.getActivity().setIntent(testIntent);
    }

    @Test
    public void CropResultsTest()
    {
        onView(withId(R.id.yieldResultTextView)).check(matches(withText("41.0")));
    }
}
