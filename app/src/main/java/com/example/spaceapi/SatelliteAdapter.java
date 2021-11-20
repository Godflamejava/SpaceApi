package com.example.spaceapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SatelliteAdapter extends RecyclerView.Adapter<SatelliteAdapter.ViewHolder> {

    ArrayList<Satellite> satellites;
    Context context;

    public SatelliteAdapter(Context context,ArrayList<Satellite> satellites){
        this.context=context;
        this.satellites=satellites;
    }

    @NonNull
    @Override
    public SatelliteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.satellite_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull SatelliteAdapter.ViewHolder holder, int position) {
    final Satellite satellite=satellites.get(position);
    holder.sat_name.setText(satellite.name);
    holder.sat_description.setText(satellite.getDescription());
    holder.more.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context,DataShow.class);
            intent.putExtra("name",satellite.name);
            intent.putExtra("description",satellite.getDescription());
            intent.putExtra("id",satellite.getId());
            context.startActivity(intent);

        }
    });
    }

    @Override
    public int getItemCount() {
        return satellites.size() ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView sat_name,sat_description;
        ImageView more;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sat_description=itemView.findViewById(R.id.sat_description);
            sat_name=itemView.findViewById(R.id.sat_name);
            more=itemView.findViewById(R.id.more);
        }

    }
}
