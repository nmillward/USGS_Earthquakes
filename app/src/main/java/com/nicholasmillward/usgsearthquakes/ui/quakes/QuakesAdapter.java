package com.nicholasmillward.usgsearthquakes.ui.quakes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicholasmillward.usgsearthquakes.R;
import com.nicholasmillward.usgsearthquakes.data.model.Quake;
import com.nicholasmillward.usgsearthquakes.data.model.QuakeData;
import com.nicholasmillward.usgsearthquakes.utils.ItemClickListener;

/**
 * Created by nmillward on 6/22/18.
 */

public class QuakesAdapter extends RecyclerView.Adapter<QuakesAdapter.QuakesViewHolder> {

    private ItemClickListener itemClickListener;
    private Quake quakes;

    public QuakesAdapter(Quake quakes, ItemClickListener itemClickListener) {
        this.quakes = quakes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public QuakesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quake, parent, false);

        final QuakesViewHolder quakesViewHolder = new QuakesViewHolder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(view, quakesViewHolder.getAdapterPosition());
            }
        });

        return quakesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuakesViewHolder holder, int position) {

        QuakeData quakeData = quakes.getQuakeData().get(position);

        holder.magnitude.setText((int) quakeData.getMag());
        holder.location.setText(quakeData.getLocation());
        holder.time.setText((int) quakeData.getTime()); //TODO: Need to convert unix timestamp

    }

    @Override
    public int getItemCount() {

        return quakes.getQuakeData().size();

    }

    public class QuakesViewHolder extends RecyclerView.ViewHolder {

        private TextView magnitude, location, time;

        public QuakesViewHolder(View itemView) {
            super(itemView);

            magnitude = itemView.findViewById(R.id.tv_magnitude);
            location = itemView.findViewById(R.id.tv_location);
            time = itemView.findViewById(R.id.tv_time);

        }
    }
}
