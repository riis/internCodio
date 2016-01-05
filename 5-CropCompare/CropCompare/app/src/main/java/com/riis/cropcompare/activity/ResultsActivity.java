package com.riis.cropcompare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.riis.cropcompare.R;
import com.riis.cropcompare.model.AvailableCropResponse;
import com.riis.cropcompare.model.HandleResponseInterface;
import com.riis.cropcompare.model.Vault;
import com.riis.cropcompare.util.GetCropCostTask;

import java.util.HashMap;
import java.util.Map;

public class ResultsActivity extends Activity implements HandleResponseInterface
{
    private final Map<String, String> mCosts = new HashMap<>();

    private boolean mPriceFound = false;
    private boolean mYieldFound = false;
    private float mPriceData;
    private float mYieldData;
    private int mAcreage;
    private String mCropSelected;
    private TextView mCostTextView;
    private TextView mPriceTextView;
    private TextView mTotalTextView;
    private TextView mYieldTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        mAcreage = Integer.parseInt(intent.getStringExtra("acreage"));
        String stateSelected = intent.getStringExtra("stateSelected");
        mCropSelected = intent.getStringExtra("cropSelected");

        mPriceTextView = (TextView)findViewById(R.id.priceResultTextView);
        mYieldTextView = (TextView)findViewById(R.id.yieldResultTextView);
        mTotalTextView = (TextView)findViewById(R.id.totalResultTextView);
        mCostTextView = (TextView)findViewById(R.id.costResultTextView);

        setUpCosts();

        //mProgressBar.setVisibility(View.VISIBLE);

        new GetCropCostTask(this, true).execute(Vault.getCropYieldUrl(stateSelected, mCropSelected));
    }

    @Override
    public void handleCropResponse(AvailableCropResponse response) {
//        if(yieldRequest)
//        {
//            mYieldData = new Util().parseDataResponse(response);
//            mYieldFound = true;
//        }
//        else
//        {
//            mPriceData = new Util().parseDataResponse(response);
//            mPriceFound = true;
//        }
//
//        if(mPriceFound && mYieldFound)
//        {
//            setResultText();
//            //mProgressBar.setVisibility(View.GONE);
//        }
    }

    private void setUpCosts() {
        mCosts.put("CORN", String.valueOf(287.72));
        mCosts.put("SOYBEANS", String.valueOf(159.51));
        mCosts.put("WHEAT", String.valueOf(105.39));
        mCosts.put("COTTON", String.valueOf(339.97));
        mCosts.put("RICE", String.valueOf(401.34));
        mCosts.put("SORGHUM", String.valueOf(117.71));
    }

    private void setResultText() {
        String placeholder = "%.02f";
        float costOfCrop = 0;
        boolean costFound = false;
        try
        {
            costOfCrop = Float.parseFloat(mCosts.get(mCropSelected.toUpperCase()));
            costFound = true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        mYieldTextView.setText(String.valueOf(mYieldData * mAcreage));
        mPriceTextView.setText(String.format(placeholder, mPriceData));
        if(costFound)
        {
            mCostTextView.setText("$" + String.format(placeholder, costOfCrop * mAcreage));
        }
        else
        {
            mCostTextView.setText("N/A");
        }

        mTotalTextView.setText("$" + String.format(placeholder,
                (mPriceData * mYieldData * mAcreage) - (costOfCrop * mAcreage)));
    }
}
