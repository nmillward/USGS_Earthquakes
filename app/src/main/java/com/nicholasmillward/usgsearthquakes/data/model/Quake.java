package com.nicholasmillward.usgsearthquakes.data.model;

import android.util.SparseArray;

/**
 * Created by nmillward on 6/21/18.
 */

public class Quake {

    private SparseArray<QuakeData> quakeData;

    public Quake() {
    }

    public Quake(SparseArray<QuakeData> quakeData) {
        this.quakeData = quakeData;
    }

    public SparseArray<QuakeData> getQuakeData() {
        return quakeData;
    }

    public void setQuakeData(SparseArray<QuakeData> quakeData) {
        this.quakeData = quakeData;
    }
}
