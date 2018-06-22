package com.nicholasmillward.usgsearthquakes.ui.quakes;

import com.nicholasmillward.usgsearthquakes.data.model.Quake;

import java.util.List;

/**
 * Created by nmillward on 6/21/18.
 */

public interface QuakesContract {

    interface View {

        void showQuakes(List<Quake> quakes);
        void showErrorMessage(String error);
        void showQuakeDetails(Quake quake);
        void showLoadingIndicator();
        void stopLoadingIndicator();
        void clearQuakes();

    }

    interface Presenter {

        void loadQuakes(boolean remoteRequired);

    }

}
