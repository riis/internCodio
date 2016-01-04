package com.riis.cropcompare.request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: by solyss on 10/29/2015.
 */
public class RequestResponseParsing {
    private StringBuilder response;

    public RequestResponseParsing(StringBuilder response) {
        this.response = response;
    }

    public List parseMetaResponse() {
        return Arrays.asList(response.toString().split("\\[\"")[1].split("\"\\]")[0].replace("\"", "").split(","));
    }

    public float parseDataResponse() {
        String val = "value";
        String data = "data";

        float result = 0;
        String value = null;

        Map<String, Map<String, String>[]> map = new Gson().fromJson(response.toString(),
                new TypeToken<HashMap<String, HashMap<String, String>[]>>() {
        }.getType());

        for (int mapIndex = 0; mapIndex < map.get(data).length; mapIndex++) {
            if (!map.get(data)[mapIndex].get("reference_period_desc").contains("-")) {
                value = map.get(data)[mapIndex].get(val).replace(",", "");
            }
        }

        try {
            result = Float.parseFloat(value);
        } catch (Exception e) {
            Log.d("RequestResponseParsing", e.toString());
        }
        return result;
    }
}