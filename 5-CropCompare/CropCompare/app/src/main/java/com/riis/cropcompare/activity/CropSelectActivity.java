package com.riis.cropcompare.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.riis.cropcompare.R;
import com.riis.cropcompare.model.HandleResponseInterface;
import com.riis.cropcompare.model.Vault;
import com.riis.cropcompare.util.GetResultsTask;
import com.riis.cropcompare.util.Util;

import java.util.List;

public class CropSelectActivity extends Activity implements HandleResponseInterface
{
    private Button[] mCropButtons;
    private List<String> mCrops;
    private List<String> mPriceReceivedResponse = null;
    private Util mUtil = new Util();
    private String mAcreage;
    private String mStateSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_select);

        Intent intent = getIntent();
        mAcreage = intent.getStringExtra("acreage");
        mStateSelected = intent.getStringExtra("state");

        mCropButtons = new Button[]
                {
                        (Button)findViewById(R.id.crop_1), (Button)findViewById(R.id.crop_2),
                        (Button)findViewById(R.id.crop_3), (Button)findViewById(R.id.crop_4),
                        (Button)findViewById(R.id.crop_5), (Button)findViewById(R.id.crop_6),
                        (Button)findViewById(R.id.crop_7), (Button)findViewById(R.id.crop_8),
                        (Button)findViewById(R.id.crop_9), (Button)findViewById(R.id.crop_10),
                        (Button)findViewById(R.id.crop_11), (Button)findViewById(R.id.crop_12),
                        (Button)findViewById(R.id.crop_13)
                };

        new GetResultsTask(this, false).execute(Vault.getPriceReceivedURL(mStateSelected));
        new GetResultsTask(this, false).execute(Vault.getYieldURL(mStateSelected));
    }

    @Override
    public void handleResponse(String response, boolean yieldRequest)
    {
        if(mPriceReceivedResponse == null)
        {
            mPriceReceivedResponse = mUtil.parseMetaResponse(response);
        }
        else
        {
            List<String> yieldReceivedResponse = mUtil.parseMetaResponse(response);
            mCrops = mUtil.compareCropLists(mPriceReceivedResponse, yieldReceivedResponse);

            setButtons();
        }
    }

    private void setButtons()
    {
        for (int buttonIndex = 0; buttonIndex < mCropButtons.length; buttonIndex++)
        {
            if (buttonIndex < mCrops.size())
            {
                setButtonText(mCropButtons[buttonIndex], mCrops.get(buttonIndex), buttonIndex);
            }
            else
            {
                mCropButtons[buttonIndex].setVisibility(View.GONE);
            }
        }
    }

    private void setButtonText(Button button, String text, final int index)
    {
        button.setText(text);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(CropSelectActivity.this, ResultsActivity.class);
                intent.putExtra("acreage", mAcreage);
                intent.putExtra("stateSelected", mStateSelected);
                intent.putExtra("cropSelected", mCrops.get(index));
                startActivity(intent);
            }
        });
    }
}
