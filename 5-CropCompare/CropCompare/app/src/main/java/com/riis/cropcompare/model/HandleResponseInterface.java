package com.riis.cropcompare.model;

/**
 * author: solyss on 10/29/2015.
 */
public interface HandleResponseInterface {
    void taskStart();
    void handleCropResponse(Object response, Boolean yieldRequest);
}
