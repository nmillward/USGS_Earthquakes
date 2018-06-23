package com.nicholasmillward.usgsearthquakes.data.model;

/**
 * Created by nmillward on 6/21/18.
 */

public class Quake {

    private float mag;
    private String location;
    private long time;
    private String url;

    public Quake(float mag, String location, long time, String url) {
        this.mag = mag;
        this.location = location;
        this.time = time;
        this.url = url;
    }

    public float getMag() {
        return mag;
    }

    public void setMag(float mag) {
        this.mag = mag;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
