package com.nicholasmillward.usgsearthquakes.ui.quakes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nicholasmillward.usgsearthquakes.R;
import com.nicholasmillward.usgsearthquakes.data.source.QuakeLoader;
import com.nicholasmillward.usgsearthquakes.data.model.Quake;
import com.nicholasmillward.usgsearthquakes.utils.ItemClickListener;
import com.nicholasmillward.usgsearthquakes.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

public class QuakesActivity extends AppCompatActivity implements QuakesContract.View {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ConstraintLayout constraintLayout;

    private QuakesAdapter adapter;
    private QuakesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quakes);

        recyclerView = findViewById(R.id.rv_quakes);
        refreshLayout = findViewById(R.id.layout_refresh);
        constraintLayout = findViewById(R.id.layout_quakesactivity);

        setupPresenter();
        setupWidgets();

        if (isOnline()) {
            presenter.start();
        } else {
            presenter.handleNetworkLoss();
        }

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        presenter.detachView();

    }

    private void setupPresenter() {

        presenter = new QuakesPresenter(this, new QuakeLoader(getApplicationContext()),
                getSupportLoaderManager());
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
        DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(divider);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFreshData();
            }
        });

    }

    private boolean isOnline() {

        return NetworkUtils.isNetworkAvailable(this);

    }

    private void getFreshData() {

        if (isOnline()) {
            if (presenter.isManagerAvailable()) {
                presenter.loadQuakes(true);
            } else {
                presenter.start();
            }
        } else {
            presenter.handleNetworkLoss();
        }

    }

    @Override
    public void showQuakes(List<Quake> quakes) {

        adapter.replaceData(quakes);

    }

    @Override
    public void showErrorMessage(String error) {

        Snackbar snackbar = Snackbar.make(constraintLayout, error, Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getFreshData();
                    }
                });
        snackbar.getView().setBackgroundResource(android.R.color.holo_red_light);
        snackbar.setActionTextColor(getResources().getColor(android.R.color.white));
        snackbar.show();

    }

    @Override
    public void showQuakeDetails(Quake quake) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(quake.getUrl()));
        if (intent.resolveActivity(getPackageManager()) != null)
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

        adapter.clearData();

    }

}
