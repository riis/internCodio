package com.riis.cropcompare.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author: solyss on 10/29/2015.
 */
public class MakeRequest
{
    public String sendGet(String url)
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
