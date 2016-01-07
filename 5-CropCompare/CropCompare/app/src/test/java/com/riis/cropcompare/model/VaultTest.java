package com.riis.cropcompare.model;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SmallTest
public class VaultTest
{
    private static final String CROP_AVAILABILITY_URL =
            "http://nass-api.azurewebsites.net/api/get_dependent_param_"
            + "values?distinctParams=commodity_desc&state_name=NORTH%20CAROLINA"
            + "&statisticcat_desc=YIELD&year=2014&freq_desc=ANNUAL&agg_level_desc=ST"
            + "ATE&group_desc=FIELD%20CROPS";

    private static final String CROP_YIELD_URL =
            "http://nass-api.azurewebsites.net/api/api_get?agg_level_desc=STATE" +
                    "&year=2014&freq_desc=ANNUAL&sector_desc=CROPS&group_desc=FIELD%20CROPS&commodity_desc=CORN"
                    + "&state_name=NORTH%20CAROLINA&statisticcat_desc=YIELD";

    private static final String CROP_PRICE_URL =
            "http://nass-api.azurewebsites.net/api/api_get?agg_level_desc" +
                    "=STATE&year=2014&freq_desc=ANNUAL&sector_desc=CROPS&group_desc=FIELD%20CROPS&commodity_desc=CORN"
                    + "&state_name=NORTH%20CAROLINA&statisticcat_desc=PRICE%20RECEIVED";

    @Test
    public void CropAvailabilityURLTest()
    {
        String northCarolinaCropAvailability = Vault.getCropAvailabilityURL("North Carolina");
        assertEquals(CROP_AVAILABILITY_URL, northCarolinaCropAvailability);
    }

    @Test
    public void CropYieldURLTest()
    {
        String northCarolinaCornYield = Vault.getCropYieldUrl("North Carolina", "CORN");
        assertEquals(CROP_YIELD_URL, northCarolinaCornYield);
    }

    @Test
    public void CropPriceURLTest()
    {
        String northCarolinaCornPrice = Vault.getCropPriceUrl("North Carolina", "CORN");
        assertEquals(CROP_PRICE_URL, northCarolinaCornPrice);
    }
}
