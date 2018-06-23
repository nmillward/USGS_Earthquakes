package com.nicholasmillward.usgsearthquakes.ui.quakes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.nicholasmillward.usgsearthquakes.data.api.QuakeLoader;
import com.nicholasmillward.usgsearthquakes.data.model.Quake;

import java.util.List;

/**
 * Created by nmillward on 6/21/18.
 *
 * reference: https://github.com/googlesamples/android-architecture/blob/deprecated-todo-mvp-loaders/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/tasks/TasksPresenter.java
 */

public class QuakesPresenter implements QuakesContract.Presenter<QuakesContract.View>,
        LoaderManager.LoaderCallbacks<List<Quake>> {

    private static final int QUAKE_LOADER_ID = 1001;

    private QuakesContract.View view;
    private QuakeLoader loader;
    private LoaderManager manager;
    private List<Quake> quakes;

    public QuakesPresenter(QuakesContract.View view, QuakeLoader loader, LoaderManager manager) {
        this.view = view;
        this.loader = loader;
        this.manager = manager;
    }

    @Override
    public void start() {
        manager.initLoader(QUAKE_LOADER_ID, null, this);
    }

    @Override
    public void attachView(QuakesContract.View view) {
        this.view = view;
    }

    @Override
    public void loadQuakes(boolean remoteRequired) {
        if (remoteRequired) {
            loader.forceLoad();
            Log.d("PRESENTER", "Fresh data requested");
        } else {
            view.showQuakes(quakes);
        }
    }

    @Override
    public void handleNetworkLoss() {
        if (quakes != null) {
            view.showQuakes(quakes);
        }
        view.stopLoadingIndicator();
        view.showErrorMessage("No network connection");
    }

    public void itemClicked(Quake quake) {
        view.showQuakeDetails(quake);
    }

    @NonNull
    @Override
    public Loader<List<Quake>> onCreateLoader(int id, @Nullable Bundle args) {
        view.showLoadingIndicator();
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Quake>> loader, List<Quake> data) {
        view.stopLoadingIndicator();

        quakes = data;
        if (quakes == null) {
            view.showErrorMessage("Quake data unavailable");
        } else {
            view.showQuakes(data);
            Log.d("PRESENTER", data.toString());
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Quake>> loader) {

    }
}
