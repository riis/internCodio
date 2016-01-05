package com.riis.cropcompare.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.riis.cropcompare.R;
import com.riis.cropcompare.model.CropDetailResponse;
import com.riis.cropcompare.model.CropResults;
import com.riis.cropcompare.model.HandleResponseInterface;
import com.riis.cropcompare.model.Vault;
import com.riis.cropcompare.util.GetCropDetailsTask;

public class ResultsActivity extends Activity implements HandleResponseInterface
{
    private CropResults mCropResults;
    private boolean mPriceFound = false;
    private boolean mYieldFound = false;
    private int mAcreage;
    private String mCropSelected;
    private TextView mCostTextView;
    private TextView mPriceTextView;
    private TextView mTotalTextView;
    private TextView mYieldTextView;
    private ProgressDialog mProgressDialog;

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

        mCropResults = new CropResults();

        setUpCosts();

        new GetCropDetailsTask(this, true).execute(Vault.getCropYieldUrl(stateSelected, mCropSelected));
        new GetCropDetailsTask(this, true).execute(Vault.getCropPriceUrl(stateSelected, mCropSelected));
    }

    @Override
    public void taskStart() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void handleCropResponse(Object response, Boolean yieldRequest) {
        if(yieldRequest)
        {
            mCropResults.setYield(((CropDetailResponse)response).value);
            mYieldFound = true;
        }
        else
        {
            mCropResults.setPricePerBU(((CropDetailResponse)response).value);
            mPriceFound = true;
        }

        if(mPriceFound && mYieldFound)
        {
            setResultText();
            mProgressDialog.dismiss();
        }
    }

    private void setUpCosts() {
//        mCosts.put("CORN", String.valueOf(287.72));
//        mCosts.put("SOYBEANS", String.valueOf(159.51));
//        mCosts.put("WHEAT", String.valueOf(105.39));
//        mCosts.put("COTTON", String.valueOf(339.97));
//        mCosts.put("RICE", String.valueOf(401.34));
//        mCosts.put("SORGHUM", String.valueOf(117.71));
    }

    private void setResultText() {
//        String placeholder = "%.02f";
//        float costOfCrop = 0;
//        boolean costFound = false;
//        try
//        {
//            costOfCrop = Float.parseFloat(mCosts.get(this.mCropSelected.toUpperCase()));
//            costFound = true;
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//        }
//
//        mYieldTextView.setText(mYieldTextView.getText() + String.valueOf(mYieldData * mAcreage));
//        mPriceTextView.setText(mPriceTextView.getText() + String.format(placeholder, mPriceData));
//        if(costFound)
//        {
//            mCostTextView.setText(mCostTextView.getText() + "$" + String.format(placeholder,
//                    costOfCrop * mAcreage));
//        }
//        else
//        {
//            mCostTextView.setText(mCostTextView.getText() + "N/A");
//        }
//
//        mTotalTextView.setText(mTotalTextView.getText() + "$" + String.format(placeholder,
//                (mPriceData * (mYieldData * mAcreage)) - (costOfCrop * mAcreage)));
    }
}
