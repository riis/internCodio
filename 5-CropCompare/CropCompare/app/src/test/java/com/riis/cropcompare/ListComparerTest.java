package com.riis.cropcompare;

import com.riis.cropcompare.misc.ListComparer;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by solyss on 10/30/2015.
 */
public class ListComparerTest {

    private ListComparer listComparer = new ListComparer();

    private List list1 = Arrays.asList("CORN", "COTTON", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");
    private List list2 = Arrays.asList("CORN", "JENKINS", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");
    private List wrongResponse = Arrays.asList("CORN", "COTTON", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");
    private List correctResponse = Arrays.asList("CORN", "HAY", "OATS", "PEANUTS", "SOYBEANS", "WHEAT");

    @Test
    public void listComparisonValidator() throws Exception {

        assertEquals(correctResponse, listComparer.compareCropLists(list1, list2));
    }
}
