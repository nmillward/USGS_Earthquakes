package com.nicholasmillward.usgsearthquakes.data.api;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

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
        if (quakes != null) {
            deliverResult(quakes);
        } else {
            forceLoad();
        }
    }

    @Override
    public List<Quake> loadInBackground() {
        List<Quake> data = QuakeHttpHandler.fetchQuakeData();
        isLoadInBackgroundCanceled();
        Log.d("QUAKE LOADER", "LOAD IN BACKGROUND");
        return data;
    }

    @Override
    public void deliverResult(List<Quake> data) {
        quakes = data;
        Log.d("QUAKE LOADER", "DELIVER RESULT");
        super.deliverResult(data);
    }

    @Override
    public void onCanceled(@Nullable List<Quake> data) {
        Log.d("QUAKE LOADER", "ON CANCELED");
        super.onCanceled(data);
    }
}
