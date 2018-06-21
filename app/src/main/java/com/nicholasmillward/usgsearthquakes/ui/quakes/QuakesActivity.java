package com.nicholasmillward.usgsearthquakes.ui.quakes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nicholasmillward.usgsearthquakes.R;
import com.nicholasmillward.usgsearthquakes.data.model.Quake;

import java.util.List;

public class QuakesActivity extends AppCompatActivity implements QuakesContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quakes);
    }

    @Override
    public void showQuakes(List<Quake> quakes) {

    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void showQuakeDetails(Quake quake) {

    }

    @Override
    public void showLoadingIndicator() {
        
    }

    @Override
    public void stopLoadingIndicator() {

    }

    @Override
    public void clearQuakes() {

    }
}
