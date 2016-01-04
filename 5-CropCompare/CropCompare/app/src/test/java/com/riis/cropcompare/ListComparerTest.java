package com.riis.cropcompare;

import com.riis.cropcompare.util.Util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListComparerTest
{
    private Util mUtil = new Util();

    private List<String> mList1 = Arrays.asList("CORN", "COTTON", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");
    private List<String> mList2 = Arrays.asList("CORN", "JENKINS", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");
    private List<String> mWrongResponse = Arrays.asList("CORN", "COTTON", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");
    private List<String> mCorrectResponse = Arrays.asList("CORN", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");

    @Test
    public void listComparisonValidator() throws Exception
    {
        assertEquals(mCorrectResponse, mUtil.compareCropLists(mList1, mList2));
    }
}
