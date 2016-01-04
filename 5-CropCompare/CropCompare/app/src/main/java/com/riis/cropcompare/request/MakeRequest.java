package com.riis.cropcompare.request;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.riis.cropcompare.misc.HandleResponseInterface;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author: solyss on 10/29/2015.
 */
public class MakeRequest {
    private String url;
    private HandleResponseInterface handleResponseInterface;
    private Activity activity;
    private boolean yieldRequest;

    public MakeRequest (String url, HandleResponseInterface handleResponseInterface, Activity activity, boolean yieldRequest) {
        this.url = url;
        this.handleResponseInterface = handleResponseInterface;
        this.activity = activity;
        this.yieldRequest = yieldRequest;
    }

    public void gatherData() {
        GetResultsASYNC task = new GetResultsASYNC();
        task.execute(new String[]{this.url});
    }

    public void sendGet(String url) {
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            final StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            this.activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    handleResponseInterface.handleResponse(response, yieldRequest);
                }
            });
        } catch(Exception e) {
            Log.d("SendGet", e.toString());
        }
    }

    private class GetResultsASYNC extends AsyncTask<String[], Void, String> {
        @Override
        protected String doInBackground(String[]... vars) {
            try {
                sendGet(vars[0][0]);
            } catch (Exception e) {
                Log.d("MakeRequest", e.toString());
            }
            return null;
        }
    }
}
