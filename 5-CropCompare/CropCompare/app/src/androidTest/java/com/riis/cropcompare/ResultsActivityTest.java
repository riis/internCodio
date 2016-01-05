package com.riis.cropcompare;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.riis.cropcompare.activity.ResultsActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class ResultsActivityTest
{
    @Rule
    public ActivityTestRule<ResultsActivity> mResultsActivityRule = new ActivityTestRule<>(ResultsActivity.class, false, false);

    @Before
    public void setUp()
    {
        Intent testIntent = new Intent();
        testIntent.putExtra("stateSelected", "Michigan");
        testIntent.putExtra("acreage", "2");
        testIntent.putExtra("cropSelected", "CORN");
        mResultsActivityRule.launchActivity(testIntent);
    }

    @Test
    public void CropResultsTest()
    {
        onView(withId(R.id.yieldResultTextView)).check(matches(withText("41.0")));
        onView(withId(R.id.priceResultTextView)).check(matches(withText("3.65")));
        onView(withId(R.id.costResultTextView)).check(matches(withText("$7.30")));
        onView(withId(R.id.totalResultTextView)).check(matches(withText("$142.35")));
    }
}
