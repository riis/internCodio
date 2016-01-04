package com.riis.cropcompare.util;

import android.os.AsyncTask;

import com.riis.cropcompare.model.AvailableCropResponse;
import com.riis.cropcompare.model.HandleResponseInterface;

public class GetCropCostTask extends AsyncTask<String, Void, AvailableCropResponse>
{
    private boolean mYieldRequest;
    private HandleResponseInterface mHandleResponseInterface;

    public GetCropCostTask(HandleResponseInterface handleResponseInterface, boolean yieldRequest)
    {
        mHandleResponseInterface = handleResponseInterface;
        mYieldRequest = yieldRequest;
    }

    @Override
    protected AvailableCropResponse doInBackground(String... vars)
    {
        if(vars.length != 1)
        {
            return null;
        }

        return new HttpHelper().getCropCost(vars[0]);
    }

    @Override
    protected void onPostExecute(AvailableCropResponse result)
    {
        super.onPostExecute(result);
        mHandleResponseInterface.handleCropResponse(result);
    }
}
