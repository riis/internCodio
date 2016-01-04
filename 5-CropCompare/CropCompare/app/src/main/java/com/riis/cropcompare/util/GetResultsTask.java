package com.riis.cropcompare.util;

import android.os.AsyncTask;

import com.riis.cropcompare.model.HandleResponseInterface;

public class GetResultsTask extends AsyncTask<String, Void, String>
{
    private boolean mYieldRequest;
    private HandleResponseInterface mHandleResponseInterface;

    public GetResultsTask(HandleResponseInterface handleResponseInterface, boolean yieldRequest)
    {
        mHandleResponseInterface = handleResponseInterface;
        mYieldRequest = yieldRequest;
    }

    @Override
    protected String doInBackground(String... vars)
    {
        if(vars.length != 1)
        {
            return null;
        }

        return new MakeRequest().sendGet(vars[0]);
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
        mHandleResponseInterface.handleResponse(result, mYieldRequest);
    }
}
