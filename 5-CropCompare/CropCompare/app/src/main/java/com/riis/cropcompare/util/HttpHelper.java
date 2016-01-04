package com.riis.cropcompare.util;

import android.util.Log;

import com.google.gson.Gson;
import com.riis.cropcompare.model.AvailableCropResponse;

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
    public AvailableCropResponse getAvailableCrops(String url) {
        try {
            Gson gson = new Gson();
            String serverResponse = getResponse(url);
            JSONObject jsonResponse = new JSONObject(serverResponse);
            JSONObject availableCropData = jsonResponse.getJSONArray("data").getJSONObject(0);

            return gson.fromJson(availableCropData.toString(), AvailableCropResponse.class);
        } catch (JSONException e) {
            return null;
        }
    }

    public AvailableCropResponse getCropCost(String url) {
        try {
            Gson gson = new Gson();
            String serverResponse = getResponse(url);
            Log.d("Inside Crop cost", serverResponse);
            JSONObject jsonResponse = new JSONObject(serverResponse);
            JSONObject availableCropData = jsonResponse.getJSONArray("data").getJSONObject(0);

            return gson.fromJson(availableCropData.toString(), AvailableCropResponse.class);
        } catch (JSONException e) {
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
            e.printStackTrace();
        }

        return response;
    }
}
