package com.nicholasmillward.usgsearthquakes.data.model;

/**
 * Created by nmillward on 6/21/18.
 */

public class Quake {

    private double mag;
    private String location;
    private long time;
    private String url;

    public Quake(double mag, String location, long time, String url) {

        this.mag = mag;
        this.location = location;
        this.time = time;
        this.url = url;

    }

    public double getMag() {
        return mag;
    }

    public String getLocation() {
        return location;
    }

    public long getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Quake{" +
                "mag=" + mag +
                ", location='" + location + '\'' +
                ", time=" + time +
                ", url='" + url + '\'' +
                '}';
    }
}
