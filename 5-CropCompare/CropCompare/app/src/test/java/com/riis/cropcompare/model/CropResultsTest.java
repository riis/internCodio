package com.riis.cropcompare.model;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@SmallTest
public class CropResultsTest
{
    private CropResults mCropResults;

    @Before
    public void setUp()
    {
        mCropResults = new CropResults();
    }

    @Test
    public void setYieldTest()
    {
        mCropResults.setAcreage(2);
        mCropResults.setYield("123");
        assertEquals("246.0", mCropResults.getYieldTotal());

        mCropResults.setAcreage(2);
        mCropResults.setYield("4,321");
        assertEquals("8642.0", mCropResults.getYieldTotal());

        mCropResults.setAcreage(3);
        mCropResults.setYield("garbage input");
        assertEquals("0.0", mCropResults.getYieldTotal());
    }

    @Test
    public void setPricePerBuTest()
    {
        mCropResults.setPricePerBU("123");
        assertEquals("123.0", mCropResults.getPricePerBuString());

        mCropResults.setPricePerBU("4,321");
        assertEquals("4321.0", mCropResults.getPricePerBuString());

        mCropResults.setPricePerBU("garbage input");
        assertEquals("0.0", mCropResults.getPricePerBuString());
    }

    @Test
    public void getCropEstimateTest()
    {
        mCropResults.setAcreage(2);
        mCropResults.setPricePerBU("123");
        assertEquals("$246.00", mCropResults.getCropEstimate());

        mCropResults.setAcreage(2);
        mCropResults.setPricePerBU("4,321");
        assertEquals("$8,642.00", mCropResults.getCropEstimate());

        mCropResults.setAcreage(3);
        mCropResults.setPricePerBU("garbage input");
        assertEquals("N/A", mCropResults.getCropEstimate());
    }

    @Test
    public void getTotalTest()
    {
        mCropResults.setAcreage(2);
        mCropResults.setPricePerBU("2");
        mCropResults.setYield("123");
        assertEquals("$488.00", mCropResults.getTotalString());

        mCropResults.setAcreage(2);
        mCropResults.setYield("4,321");
        mCropResults.setPricePerBU("2");
        assertEquals("$17,280.00", mCropResults.getTotalString());

        mCropResults.setAcreage(3);
        mCropResults.setYield("2");
        mCropResults.setPricePerBU("garbage input");
        assertEquals("$0.00", mCropResults.getTotalString());
    }
}
