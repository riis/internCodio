package com.riis.cropcompare.util;

import android.util.Log;

import com.google.gson.Gson;
import com.riis.cropcompare.model.AvailableCropResponse;
import com.riis.cropcompare.model.CropDetailResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author: solyss on 10/29/2015.
 */
public class HttpHelper
{
    private static final String ERROR_PARSING_JSON = "Error Parsing JSON";

    public AvailableCropResponse getAvailableCrops(String url) {
        try {
            Gson gson = new Gson();
            String serverResponse = getResponse(url);
            JSONObject jsonResponse = new JSONObject(serverResponse);
            JSONObject availableCropData = jsonResponse.getJSONArray("data").getJSONObject(0);

            return gson.fromJson(availableCropData.toString(), AvailableCropResponse.class);
        } catch (JSONException e) {
            Log.e(ERROR_PARSING_JSON, e.toString());
            return null;
        }
    }

    public CropDetailResponse getCropDetails(String url) {
        try {
            Gson gson = new Gson();
            String serverResponse = getResponse(url);
            JSONObject jsonResponse = new JSONObject(serverResponse);
            JSONObject cropDetailData = jsonResponse.getJSONArray("data").getJSONObject(0);
            CropDetailResponse cropDetailResponse = gson.fromJson(cropDetailData.toString(), CropDetailResponse.class);
            if (cropDetailResponse.value == null)
            {
                cropDetailResponse.value = "0";
            }

            return cropDetailResponse;
        } catch (JSONException e) {
            Log.e(ERROR_PARSING_JSON, e.toString());
            return null;
        }
    }

    private String getResponse(String url)
    {
        String response = "";
        try
        {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            final StringBuilder responseBuilder = new StringBuilder();

            while ((inputLine = in.readLine()) != null)
            {
                responseBuilder.append(inputLine);
            }
            in.close();

            response = responseBuilder.toString();
        }
        catch(Exception e)
        {
            Log.e(ERROR_PARSING_JSON, e.toString());
        }

        return response;
    }
}
