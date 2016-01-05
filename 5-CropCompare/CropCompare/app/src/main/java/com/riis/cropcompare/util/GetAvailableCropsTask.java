package com.riis.cropcompare.util;

import android.os.AsyncTask;

import com.riis.cropcompare.model.AvailableCropResponse;
import com.riis.cropcompare.model.HandleResponseInterface;

public class GetAvailableCropsTask extends AsyncTask<String, Void, AvailableCropResponse>
{
    private HandleResponseInterface mHandleResponseInterface;

    public GetAvailableCropsTask(HandleResponseInterface handleResponseInterface)
    {
        mHandleResponseInterface = handleResponseInterface;
    }

    @Override
    protected AvailableCropResponse doInBackground(String... vars)
    {
        if(vars.length != 1)
        {
            return null;
        }

        return new HttpHelper().getAvailableCrops(vars[0]);
    }

    @Override
    protected void onPostExecute(AvailableCropResponse result)
    {
        super.onPostExecute(result);
        mHandleResponseInterface.handleCropResponse(result, false);
    }
}
