package com.riis.cropcompare.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import com.riis.cropcompare.BR;

import java.text.NumberFormat;
import java.util.Locale;

public class CropResults extends BaseObservable
{
    private int mAcreage;
    private float mYield;
    private float mPricePerBU;

    public void setYield(String yield)
    {
        try
        {
            mYield = Float.valueOf(yield.replace(",", ""));
        }
        catch (NumberFormatException e)
        {
            mYield = 0;
            Log.e("Invalid value for yield", e.toString());
        }

        notifyPropertyChanged(BR.yieldTotal);
    }

    public void setPricePerBU(String pricePerBU)
    {
        try
        {
            mPricePerBU = Float.valueOf(pricePerBU.replace(",", ""));
        }
        catch (NumberFormatException e)
        {
            mPricePerBU = 0;
            Log.e("Invalid value for price", e.toString());
        }

        notifyPropertyChanged(BR.pricePerBuString);
        notifyPropertyChanged(BR.cropEstimate);
        notifyPropertyChanged(BR.totalString);
    }

    public void setAcreage(int acreage)
    {
        mAcreage = acreage;
    }

    @Bindable
    public String getCropEstimate()
    {
        if (Float.compare(mPricePerBU, 0F) == 0)
        {
            return "N/A";
        }

        return NumberFormat.getCurrencyInstance(Locale.US).format(mPricePerBU * mAcreage);
    }

    @Bindable
    public String getPricePerBuString()
    {
        return String.valueOf(mPricePerBU);
    }

    @Bindable
    public String getYieldTotal()
    {
        return String.valueOf(mAcreage * mYield);
    }

    @Bindable
    public String getTotalString()
    {
        return NumberFormat.getCurrencyInstance(Locale.US).format(
                (mPricePerBU * mYield * mAcreage) - (mPricePerBU * mAcreage)
        );
    }

    public float getPricePerBu() {
        return mPricePerBU;
    }
}
