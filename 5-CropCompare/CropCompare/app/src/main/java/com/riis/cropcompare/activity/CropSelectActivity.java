package com.riis.cropcompare.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.riis.cropcompare.R;
import com.riis.cropcompare.model.AvailableCropResponse;
import com.riis.cropcompare.model.HandleResponseInterface;
import com.riis.cropcompare.model.Vault;
import com.riis.cropcompare.util.GetAvailableCropsTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CropSelectActivity extends Activity implements HandleResponseInterface
{
    private LinearLayout mButtonLinearLayout;
    private List<String> mCrops;
    private String mAcreage;
    private String mStateSelected;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_select);

        Intent intent = getIntent();
        mAcreage = intent.getStringExtra("acreage");
        mStateSelected = intent.getStringExtra("state");

        mButtonLinearLayout = (LinearLayout) findViewById(R.id.crop_container);

        new GetAvailableCropsTask(this).execute(Vault.getCropAvailabilityURL(mStateSelected));
    }

    @Override
    public void taskStart() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void handleCropResponse(Object response, Boolean notUsed)
    {
        AvailableCropResponse availableCropResponse = (AvailableCropResponse) response;
        mCrops = new ArrayList<>(Arrays.asList(availableCropResponse.Values));
        setButtons();
        mProgressDialog.dismiss();
    }

    private void setButtons()
    {
        for (int buttonIndex = 0; buttonIndex < mCrops.size(); buttonIndex++)
        {
            LayoutInflater inflater = LayoutInflater.from(this);
            Button cropButton = (Button) inflater.inflate(R.layout.crop_button, null, false);
            cropButton.setText(mCrops.get(buttonIndex));
            final int finalButtonIndex = buttonIndex;

            View.OnClickListener cropButtonClickListener = new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(CropSelectActivity.this, ResultsActivity.class);
                    intent.putExtra("acreage", mAcreage);
                    intent.putExtra("stateSelected", mStateSelected);
                    intent.putExtra("cropSelected", mCrops.get(finalButtonIndex));
                    startActivity(intent);
                }
            };
            cropButton.setOnClickListener(cropButtonClickListener);

            mButtonLinearLayout.addView(cropButton);
        }
    }
}
