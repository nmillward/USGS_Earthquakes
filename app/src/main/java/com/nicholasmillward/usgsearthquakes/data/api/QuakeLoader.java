package com.nicholasmillward.usgsearthquakes.data.api;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.nicholasmillward.usgsearthquakes.data.model.Quake;

import java.util.List;

/**
 * Created by nmillward on 6/22/18.
 */

public class QuakeLoader extends AsyncTaskLoader<List<Quake>> {

    private List<Quake> quakes;

    public QuakeLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {

        //TODO: check for cache first

        forceLoad();

    }

    @Override
    public List<Quake> loadInBackground() {
        return QuakeHttpHandler.fetchQuakeData();
    }

    @Override
    public void deliverResult(List<Quake> data) {
        super.deliverResult(data);
    }
}
