package com.nicholasmillward.usgsearthquakes.data.api;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.nicholasmillward.usgsearthquakes.data.model.Quake;

/**
 * Created by nmillward on 6/22/18.
 */

public class QuakeLoader extends AsyncTaskLoader<Quake> {

    private Quake quakes;

    public QuakeLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {

        //TODO: check for cache first

        forceLoad();

    }

    @Override
    public Quake loadInBackground() {
        return QuakeHttpHandler.fetchQuakeData();
    }

    @Override
    public void deliverResult(Quake data) {
        super.deliverResult(data);
    }
}
