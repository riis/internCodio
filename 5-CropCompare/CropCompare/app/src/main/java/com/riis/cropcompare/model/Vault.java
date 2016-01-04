package com.riis.cropcompare.model;

public class Vault
{
    public static String getPriceReceivedURL(String state)
    {
        return "http://nass-api.azurewebsites.net/api/get_dependent_"
                + "param_values?distinctParams=commodity_desc&state_name="
                + state.replace(" ", "%20").toUpperCase()
                + "&statisticcat_desc=PRICE%20RECEIVED&year=2014"
                + "&freq_desc=ANNUAL&agg_level_desc=STATE&group_desc=FIELD%20CROPS";
    }

    public static String getYieldURL(String state)
    {
        return "http://nass-api.azurewebsites.net/api/get_dependent_param_"
                + "values?distinctParams=commodity_desc&state_name="
                + state.replace(" ", "%20").toUpperCase()
                + "&statisticcat_desc=YIELD&year=2014&freq_desc=ANNUAL&agg_level_desc=ST"
                + "ATE&group_desc=FIELD%20CROPS";
    }
}
