package com.riis.cropcompare.model;

import android.databinding.Bindable;

import java.text.NumberFormat;
import java.util.Locale;

public class CropResults {
    private int mAcreage;
    private float mCropCost = 0f;
    private float mYield;
    private float mPricePerBU;
    private float mEstimatedCost;
    private float mTotal;

    public void setYield(float yield) {
        mYield = yield;
    }

    public void setPricePerBU(float pricePerBU) {
        mPricePerBU = pricePerBU;
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
    }

    public String getCropEstimate()
    {
        if (mCropCost == 0)
        {
            return "N/A";
        }

        return NumberFormat.getCurrencyInstance(Locale.US).format(mCropCost * mAcreage);
    }

    public String getPricePerBuString()
    {
        return String.valueOf(mPricePerBU);
    }

    @Bindable
    public String getYieldTotal()
    {
        return String.valueOf(mAcreage * mYield);
    }

    public String getTotalString()
    {
        mTotal = (mPricePerBU * mYield * mAcreage) - (mCropCost * mAcreage);
        return NumberFormat.getCurrencyInstance(Locale.US).format(mTotal);
    }
}
