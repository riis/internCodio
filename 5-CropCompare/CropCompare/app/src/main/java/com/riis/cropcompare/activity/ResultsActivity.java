package com.riis.cropcompare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.riis.cropcompare.R;
import com.riis.cropcompare.misc.HandleResponseInterface;
import com.riis.cropcompare.request.MakeRequest;
import com.riis.cropcompare.request.RequestResponseParsing;

import java.util.HashMap;
import java.util.Map;

public class ResultsActivity extends Activity implements HandleResponseInterface {

    private int acreage;
    private String stateSelected;
    private String cropSelected;
    private ProgressBar spinner;
    private TextView yieldTextView;
    private TextView priceTextView;
    private TextView costTextView;
    private TextView totalTextView;
    private float yieldData;
    private float priceData;
    boolean yieldFound = false;
    boolean priceFound = false;
    private final Map<String, String> costs = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        priceTextView = (TextView)findViewById(R.id.priceResultTextView);
        yieldTextView = (TextView)findViewById(R.id.yieldResultTextView);
        totalTextView = (TextView)findViewById(R.id.totalResultTextView);
        costTextView = (TextView)findViewById(R.id.costResultTextView);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);

        setUpCosts();

        this.spinner.setVisibility(View.VISIBLE);

        getIntentExtras();
        String[] urls = generateURLs(this.stateSelected, this.cropSelected);

        MakeRequest priceReceivedMakeRequest = new MakeRequest(urls[0], this, this, false);
        MakeRequest yieldMakeRequest = new MakeRequest(urls[1], this, this, true);

        priceReceivedMakeRequest.gatherData();
        yieldMakeRequest.gatherData();

    }

    private String[] generateURLs(String state, String crop) {
        String yieldRequestURL = "http://nass-api.azurewebsites.net/api/api_get?agg_level_desc=STATE" +
                "&year=2014&freq_desc=ANNUAL&sector_desc=CROPS&group_desc=FIELD%20CROPS&commodity_desc=" +
                crop.replace(" ", "%20").replace(",", "%26") + "&state_name=" + state.replace(" ", "%20")
                .toUpperCase() + "&statisticcat_desc=YIELD";

        String priceRecievedRequestURL = "http://nass-api.azurewebsites.net/api/api_get?agg_level_desc" +
                "=STATE&year=2014&freq_desc=ANNUAL&sector_desc=CROPS&group_desc=FIELD%20CROPS&commodity_desc="
                + crop.replace(" ", "%20").replace(",", "%26") + "&state_name=" + state.replace(" ", "%20")
                .toUpperCase() + "&statisticcat_desc=PRICE%20RECEIVED";

        return new String[]{priceRecievedRequestURL, yieldRequestURL};
    }

    private void getIntentExtras() {
        Intent intent = getIntent();
        this.acreage = Integer.parseInt(intent.getStringExtra("acreage"));
        this.stateSelected = intent.getStringExtra("stateSelected");
        this.cropSelected = intent.getStringExtra("cropSelected");
    }

    @Override
    public void handleResponse(StringBuilder response, boolean yieldRequest) {
        RequestResponseParsing requestResponseParsing = new RequestResponseParsing(response);
        if(yieldRequest) {
            yieldData = requestResponseParsing.parseDataResponse();
            yieldFound = true;
        } else {
            priceData = requestResponseParsing.parseDataResponse();
            priceFound = true;
        }

        if(priceFound &&yieldFound) {
            setResultText();
            this.spinner.setVisibility(View.GONE);
        }
    }

    private void setUpCosts() {
        this.costs.put("CORN", String.valueOf(287.72));
        this.costs.put("SOYBEANS", String.valueOf(159.51));
        this.costs.put("WHEAT", String.valueOf(105.39));
        this.costs.put("COTTON", String.valueOf(339.97));
        this.costs.put("RICE", String.valueOf(401.34));
        this.costs.put("SORGHUM", String.valueOf(117.71));
    }

    private void setResultText() {
        String placeholder = "%.02f";
        float costOfCrop = 0;
        boolean costFound = false;
        try {
            costOfCrop = Float.parseFloat(costs.get(this.cropSelected.toUpperCase()));
            costFound = true;
        } catch(Exception e) {
            Log.d("ResultsActivity", e.toString());
        }
        yieldTextView.setText(yieldTextView.getText() + String.valueOf(yieldData * this.acreage));
        priceTextView.setText(priceTextView.getText() + String.format(placeholder, priceData));
        if(costFound)
            costTextView.setText(costTextView.getText() + "$" + String.format(placeholder, costOfCrop * this.acreage));
        else
            costTextView.setText(costTextView.getText() + "N/A");

        totalTextView.setText(totalTextView.getText() + "$" + String.format(placeholder, (priceData * (yieldData * this.acreage)) - (costOfCrop * this.acreage)));
    }
}
