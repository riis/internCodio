package com.riis.cropcompare.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.riis.cropcompare.BR;

import java.text.NumberFormat;
import java.util.Locale;

public class CropResults extends BaseObservable {
    private int mAcreage;
    private float mCropCost;
    private float mYield;
    private float mPricePerBU;
    private float mEstimatedCost;
    private float mTotal;

    public void setYield(String yield) {
        try
        {
            yield = yield.replace(",", "");
            mYield = Float.valueOf(yield);
        }
        catch (NumberFormatException e)
        {
            mYield = 0;
            e.printStackTrace();
        }

        notifyPropertyChanged(BR.yieldTotal);
    }

    public void setPricePerBU(String pricePerBU) {
        try
        {
            mPricePerBU = Float.valueOf(pricePerBU.replace(",", ""));
        }
        catch (NumberFormatException e)
        {
            mPricePerBU = 0;
            e.printStackTrace();
        }

        notifyPropertyChanged(BR.pricePerBuString);
    }

    public float getEstimatedCost() {
        return mEstimatedCost;
    }

    public float getTotal() {
        return mTotal;
    }

    public void setAcreage(int acreage)
    {
        mAcreage = acreage;
    }

    public void setCropCost(float cropCost)
    {
        mCropCost = cropCost;
        notifyPropertyChanged(BR.cropEstimate);
        notifyPropertyChanged(BR.totalString);
    }

    @Bindable
    public String getCropEstimate()
    {
        if (mCropCost == 0)
        {
            return "N/A";
        }

        return NumberFormat.getCurrencyInstance(Locale.US).format(mCropCost * mAcreage);
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
        mTotal = (mPricePerBU * mYield * mAcreage) - (mCropCost * mAcreage);
        return NumberFormat.getCurrencyInstance(Locale.US).format(mTotal);
    }
}
