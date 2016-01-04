package com.riis.cropcompare.activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.riis.cropcompare.R;
import com.riis.cropcompare.misc.HandleResponseInterface;
import com.riis.cropcompare.misc.ListComparer;
import com.riis.cropcompare.request.MakeRequest;
import com.riis.cropcompare.request.RequestResponseParsing;

import java.util.List;

public class SecondActivity extends Activity implements HandleResponseInterface {
    private String acreage;
    private List crops;
    private Button[] cropButtons;
    private String stateSelected;
    private ListComparer listComparer = new ListComparer();
    private List[] metaResponses = new List[]{null, null};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.cropButtons = new Button[]{
                (Button)findViewById(R.id.crop_1), (Button)findViewById(R.id.crop_2),
                (Button)findViewById(R.id.crop_3), (Button)findViewById(R.id.crop_4),
                (Button)findViewById(R.id.crop_5), (Button)findViewById(R.id.crop_6),
                (Button)findViewById(R.id.crop_7), (Button)findViewById(R.id.crop_8),
                (Button)findViewById(R.id.crop_9), (Button)findViewById(R.id.crop_10),
                (Button)findViewById(R.id.crop_11), (Button)findViewById(R.id.crop_12),
                (Button)findViewById(R.id.crop_13)
        };

        getIntentExtras();
        String[] urls = generateURLs(this.stateSelected);

        MakeRequest priceReceivedMakeRequest = new MakeRequest(urls[0], this, this, false);
        MakeRequest yieldMakeRequest = new MakeRequest(urls[1], this, this, true);

        priceReceivedMakeRequest.gatherData();
        yieldMakeRequest.gatherData();
    }

    private void getIntentExtras() {
        Intent intent = getIntent();
        this.acreage = intent.getStringExtra("acreage");
        this.stateSelected = intent.getStringExtra("state");
    }

    private String[] generateURLs(String state) {
        String priceReceivedRequestMetaURL = "http://nass-api.azurewebsites.net/api/get_dependent_" +
            "param_values?distinctParams=commodity_desc&state_name=" + state.
            replace(" ", "%20").toUpperCase() + "&statisticcat_desc=PRICE%20RECEIVED&year=2014" +
            "&freq_desc=ANNUAL&agg_level_desc=STATE&group_desc=FIELD%20CROPS";

        String yieldRequestMetaURL = "http://nass-api.azurewebsites.net/api/get_dependent_param_" +
            "values?distinctParams=commodity_desc&state_name=" + state.replace(" ", "%20")
            .toUpperCase() + "&statisticcat_desc=YIELD&year=2014&freq_desc=ANNUAL&agg_level_desc=ST" +
                "ATE&group_desc=FIELD%20CROPS";

        return new String[]{priceReceivedRequestMetaURL, yieldRequestMetaURL};
    }

    @Override
    public void handleResponse(StringBuilder response, boolean yieldRequest) {
        RequestResponseParsing requestResponseParsing = new RequestResponseParsing(response);
        if(this.metaResponses[0] == null) {
            this.metaResponses[0] = requestResponseParsing.parseMetaResponse();
        } else {
            this.metaResponses[1] = requestResponseParsing.parseMetaResponse();
            this.crops = listComparer.compareCropLists(this.metaResponses[0], this.metaResponses[1]);

            setButtons();
        }
    }

    private void setButtons() {
        for (int buttonIndex = 0; buttonIndex < this.cropButtons.length; buttonIndex++) {
            if (buttonIndex < this.crops.size()) {
                setButtonText(this.cropButtons[buttonIndex], this.crops.get(buttonIndex).toString(), buttonIndex);
            } else {
                hideButton(this.cropButtons[buttonIndex]);
            }
        }
    }

    private void setButtonText(Button button, String text, final int index) {
        button.setText(text);
        View.OnClickListener thisIsStupid = new View.OnClickListener() {
            public void onClick(View v) {
                goToResultsActivity(index);
            }
        };
        button.setOnClickListener(thisIsStupid);
    }

    private void hideButton(Button button) {
        button.setVisibility(View.GONE);
    }

    private void goToResultsActivity(int cropButtonIndex) {
        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(SecondActivity.this);
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("acreage", acreage);
        intent.putExtra("stateSelected", stateSelected);
        intent.putExtra("cropSelected", this.crops.get(cropButtonIndex).toString());
        startActivity(intent, transitionActivityOptions.toBundle());
    }
}
