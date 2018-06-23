package com.nicholasmillward.usgsearthquakes.ui.quakes;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nicholasmillward.usgsearthquakes.R;
import com.nicholasmillward.usgsearthquakes.data.model.Quake;
import com.nicholasmillward.usgsearthquakes.utils.ItemClickListener;

import java.util.List;

public class QuakesActivity extends AppCompatActivity implements QuakesContract.View {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    private QuakesAdapter adapter;
    private QuakesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quakes);

        recyclerView = findViewById(R.id.rv_quakes);
        refreshLayout = findViewById(R.id.refresh_layout);



    }

    private void setupPresenter() {

    }

    private void setupWidgets() {

        adapter = new QuakesAdapter(new Quake(), new ItemClickListener() {
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
}
