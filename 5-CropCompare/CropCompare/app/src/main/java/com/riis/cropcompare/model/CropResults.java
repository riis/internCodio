package com.riis.cropcompare.model;

public class CropResults {
    private float mYield;
    private float mPricePerBU;
    private float mEstimatedCost;
    private float mTotal;

    public float getYield() {
        return mYield;
    }

    public void setYield(float yield) {
        mYield = yield;
    }

    public float getPricePerBU() {
        return mPricePerBU;
    }

    public void setPricePerBU(float pricePerBU) {
        mPricePerBU = pricePerBU;
    }

    public float getEstimatedCost() {
        return mEstimatedCost;
    }

    public void setEstimatedCost(float estimatedCost) {
        mEstimatedCost = estimatedCost;
    }

    public float getTotal() {
        return mTotal;
    }

    public void setTotal(float total) {
        mTotal = total;
    }
}
