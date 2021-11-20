package com.example.spaceapi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.ViewHolder> {
    ArrayList<JSONObject> objects;
    Context context;
public ObjectAdapter(Context context,ArrayList<JSONObject> objects){
    this.objects=objects;
   this.context=context;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.visual_pass_item, parent, false);
        return new ObjectAdapter .ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    JSONObject jsonObject = objects.get(position);
        Log.i("mary333",""+objects);
        try {
            holder.dtstartAz.setText(jsonObject.getString("startAz"));
            holder.dtstartAzComps.setText(jsonObject.getString("startAzCompass"));
//            holder.dtstartEl.setText(jsonObject.getString("startEl"));
            holder.dtStartUTC.setText(jsonObject.getString("startUTC"));
            holder.dtendAzimuth.setText(jsonObject.getString("endAz"));
            holder.dtendAzComps.setText(jsonObject.getString("endAzCompass"));
//            holder.dtendEl.setText(jsonObject.getString("endEl"));
            holder.dtendUTC.setText(jsonObject.getString("endUTC"));
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dtstartAz,dtstartAzComps,dtstartEl,dtStartUTC,dtendAzimuth,dtendAzComps,dtendEl,dtendUTC;
        Button map;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dtstartAz=itemView.findViewById(R.id.dtstartAz);
            dtstartAzComps=itemView.findViewById(R.id.dtstartAzComps);
            dtStartUTC=itemView.findViewById(R.id.dtStartUTC);
            dtstartEl=itemView.findViewById(R.id.dtstartEl);
            dtendAzimuth=itemView.findViewById(R.id.dtendAzimuth);
            dtendAzComps=itemView.findViewById(R.id.dtendAzComps);
            dtendEl=itemView.findViewById(R.id.dtendEl);
            dtendUTC=itemView.findViewById(R.id.dtendUTC);
        }
    }
}
