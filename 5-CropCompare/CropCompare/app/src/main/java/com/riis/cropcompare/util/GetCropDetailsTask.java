package com.riis.cropcompare.util;

import android.os.AsyncTask;

import com.riis.cropcompare.model.CropDetailResponse;
import com.riis.cropcompare.model.HandleResponseInterface;

public class GetCropDetailsTask extends AsyncTask<String, Void, CropDetailResponse>
{
    private boolean mYieldRequest;
    private HandleResponseInterface mHandleResponseInterface;

    public GetCropDetailsTask(HandleResponseInterface handleResponseInterface, boolean yieldRequest)
    {
        mHandleResponseInterface = handleResponseInterface;
        mYieldRequest = yieldRequest;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mHandleResponseInterface.taskStart();
    }

    @Override
    protected CropDetailResponse doInBackground(String... vars)
    {
        if(vars.length != 1)
        {
            return null;
        }

        return new HttpHelper().getCropDetails(vars[0]);
    }

    @Override
    protected void onPostExecute(CropDetailResponse result)
    {
        super.onPostExecute(result);
        mHandleResponseInterface.handleCropResponse(result, mYieldRequest);
    }
}
