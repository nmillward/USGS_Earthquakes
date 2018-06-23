package com.nicholasmillward.usgsearthquakes.ui.quakes;

import com.nicholasmillward.usgsearthquakes.data.model.Quake;

/**
 * Created by nmillward on 6/21/18.
 */

public class QuakesPresenter implements QuakesContract.Presenter<QuakesContract.View> {

    private QuakesContract.View view;

    @Override
    public void attachView(QuakesContract.View view) {
        this.view = view;
    }

    @Override
    public void loadQuakes(boolean remoteRequired) {

    }

    public void itemClicked(Quake quake) {
        view.showQuakeDetails(quake);
    }

}
