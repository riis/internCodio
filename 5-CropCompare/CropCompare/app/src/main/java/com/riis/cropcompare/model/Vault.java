package com.riis.cropcompare.model;

public class Vault
{
    public static String getCropAvailabilityURL(String state)
    {
        return "http://nass-api.azurewebsites.net/api/get_dependent_param_"
                + "values?distinctParams=commodity_desc&state_name="
                + state.replace(" ", "%20").toUpperCase()
                + "&statisticcat_desc=YIELD&year=2014&freq_desc=ANNUAL&agg_level_desc=ST"
                + "ATE&group_desc=FIELD%20CROPS";
    }

    public static String getCropYieldUrl(String state, String crop) {
        return  "http://nass-api.azurewebsites.net/api/api_get?agg_level_desc=STATE" +
                "&year=2014&freq_desc=ANNUAL&sector_desc=CROPS&group_desc=FIELD%20CROPS&commodity_desc=" +
                crop.replace(" ", "%20").replace(",", "%26") + "&state_name=" + state.replace(" ", "%20")
                .toUpperCase() + "&statisticcat_desc=YIELD";
    }

    public static String getCropPriceUrl(String state, String crop) {
        return "http://nass-api.azurewebsites.net/api/api_get?agg_level_desc" +
            "=STATE&year=2014&freq_desc=ANNUAL&sector_desc=CROPS&group_desc=FIELD%20CROPS&commodity_desc="
            + crop.replace(" ", "%20").replace(",", "%26") + "&state_name=" + state.replace(" ", "%20")
            .toUpperCase() + "&statisticcat_desc=PRICE%20RECEIVED";
    }
}