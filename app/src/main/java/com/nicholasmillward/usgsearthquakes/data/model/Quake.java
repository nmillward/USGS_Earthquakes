package com.nicholasmillward.usgsearthquakes.data.model;

/**
 * Created by nmillward on 6/21/18.
 */

public class Quake {

    private QuakeData quakeData;

    public Quake(QuakeData quakeData) {
        this.quakeData = quakeData;
    }

    public QuakeData getQuakeData() {
        return quakeData;
    }

    public void setQuakeData(QuakeData quakeData) {
        this.quakeData = quakeData;
    }
}
