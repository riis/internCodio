package com.riis.cropcompare.util;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@SmallTest
public class UtilTest
{
    private static final List<String> WRONG_LIST_RESULT = Arrays.asList("CORN", "COTTON", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");
    private static final List<String> CORRECT_LIST_RESULT = Arrays.asList("CORN", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");

    private Util mUtil = new Util();

    private List<String> mList1 = Arrays.asList("CORN", "COTTON", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");
    private List<String> mList2 = Arrays.asList("CORN", "JENKINS", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");

    @Test
    public void ValidListComparisonTest()
    {
        assertEquals(CORRECT_LIST_RESULT, mUtil.compareCropLists(mList1, mList2));
    }

    @Test
    public void InvalidListComparistonTest()
    {
        assertNotEquals(WRONG_LIST_RESULT, mUtil.compareCropLists(mList1, mList2));
    }
}
