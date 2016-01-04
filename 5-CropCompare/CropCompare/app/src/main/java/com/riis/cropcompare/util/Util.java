package com.riis.cropcompare.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util
{
    public List<String> compareCropLists(List<String> response1, List<String> response2)
    {
        List<String> listSimilarities = new ArrayList<>();

        for(int response1Index = 0; response1Index < response1.size(); response1Index++)
        {
            if(response2.contains(response1.get(response1Index)))
            {
                listSimilarities.add(response1.get(response1Index));
            }
        }
        return listSimilarities;
    }

    public ArrayList<String> parseMetaResponse(String response)
    {
        return (ArrayList<String>) Arrays.asList(
                response.split("\\[\"")[1].split("\"\\]")[0].replace("\"", "").split(","));
    }

    public float parseDataResponse(String response)
    {
        String val = "value";
        String data = "data";

        float result = 0;
        String value = null;

        Map<String, Map<String, String>[]> map = new Gson().fromJson(response,
                new TypeToken<HashMap<String, HashMap<String, String>[]>>() {
                }.getType());

        for (int mapIndex = 0; mapIndex < map.get(data).length; mapIndex++)
        {
            if (!map.get(data)[mapIndex].get("reference_period_desc").contains("-"))
            {
                value = map.get(data)[mapIndex].get(val).replace(",", "");
            }
        }

        if(value != null)
        {
            try
            {
                result = Float.parseFloat(value);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return result;
    }
}
