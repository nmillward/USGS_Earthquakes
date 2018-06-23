package com.nicholasmillward.usgsearthquakes.ui.quakes;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.nicholasmillward.usgsearthquakes.R;
import com.nicholasmillward.usgsearthquakes.data.api.QuakeLoader;
import com.nicholasmillward.usgsearthquakes.data.model.Quake;
import com.nicholasmillward.usgsearthquakes.utils.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class QuakesActivity extends AppCompatActivity implements QuakesContract.View, LoaderManager.LoaderCallbacks<List<Quake>> {

    private static final String TAG = QuakesActivity.class.getSimpleName();
    private static final int QUAKE_LOADER_ID = 1001;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    private QuakesAdapter adapter;
    private QuakesPresenter presenter;
    private LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quakes);

        recyclerView = findViewById(R.id.rv_quakes);
        refreshLayout = findViewById(R.id.refresh_layout);

        setupPresenter();
        setupWidgets();

        loaderManager = getLoaderManager();
        loaderManager.initLoader(QUAKE_LOADER_ID, null, this);
    }

    private void setupPresenter() {

    }

    private void setupWidgets() {

        adapter = new QuakesAdapter(new ArrayList<Quake>(), new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // TODO: presenter -- itemClicked(view, position)
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO: presenter -- loadQuakes
            }
        });

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

    @Override
    public Loader<List<Quake>> onCreateLoader(int i, Bundle bundle) {
        return new QuakeLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Quake>> loader, List<Quake> quakeData) {
        Log.d(TAG, quakeData.toString());
        adapter.replaceData(quakeData);
    }

    @Override
    public void onLoaderReset(Loader<List<Quake>> loader) {

    }

}
