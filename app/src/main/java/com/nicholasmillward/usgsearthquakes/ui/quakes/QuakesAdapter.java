package com.nicholasmillward.usgsearthquakes.ui.quakes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicholasmillward.usgsearthquakes.R;
import com.nicholasmillward.usgsearthquakes.data.model.Quake;
import com.nicholasmillward.usgsearthquakes.utils.DateTimeUtils;
import com.nicholasmillward.usgsearthquakes.utils.ItemClickListener;

import java.util.List;

/**
 * Created by nmillward on 6/22/18.
 */

public class QuakesAdapter extends RecyclerView.Adapter<QuakesAdapter.QuakesViewHolder> {

    private ItemClickListener itemClickListener;
    private List<Quake> quakes;

    public QuakesAdapter(List<Quake> quakes, ItemClickListener itemClickListener) {
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

        Quake quake = quakes.get(position);

        holder.magnitude.setText((int) quake.getMag());
        holder.location.setText(quake.getLocation());
        holder.time.setText(DateTimeUtils.timestampToRelativeTime(quake.getTime()));

    }

    @Override
    public int getItemCount() {

        return quakes.size();

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
