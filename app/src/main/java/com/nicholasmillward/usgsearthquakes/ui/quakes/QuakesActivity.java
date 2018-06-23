package com.nicholasmillward.usgsearthquakes.ui.quakes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nicholasmillward.usgsearthquakes.R;
import com.nicholasmillward.usgsearthquakes.data.api.QuakeLoader;
import com.nicholasmillward.usgsearthquakes.data.model.Quake;
import com.nicholasmillward.usgsearthquakes.utils.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class QuakesActivity extends AppCompatActivity implements QuakesContract.View {

    private static final String TAG = QuakesActivity.class.getSimpleName();
    private static final int QUAKE_LOADER_ID = 1001;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    private QuakesAdapter adapter;
    private QuakesPresenter presenter;
    private QuakeLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quakes);

        recyclerView = findViewById(R.id.rv_quakes);
        refreshLayout = findViewById(R.id.refresh_layout);

        setupPresenter();
        setupWidgets();

        // TODO: check for network connection status

        // TODO: check savedInstanceState

        presenter.start();
    }



    private void setupPresenter() {
        loader = new QuakeLoader(getApplicationContext());

        presenter = new QuakesPresenter(this, loader, getSupportLoaderManager());
        presenter.attachView(this);
    }

    private void setupWidgets() {

        adapter = new QuakesAdapter(new ArrayList<Quake>(), new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                presenter.itemClicked(adapter.getItem(position));
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadQuakes(true);
            }
        });

    }

    @Override
    public void showQuakes(List<Quake> quakes) {
        adapter.replaceData(quakes);
    }

    @Override
    public void showErrorMessage(String error) {

    }

    @Override
    public void showQuakeDetails(Quake quake) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(quake.getUrl()));
        startActivity(intent);
    }

    @Override
    public void showLoadingIndicator() {
        if (!refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void stopLoadingIndicator() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void clearQuakes() {

    }

}
