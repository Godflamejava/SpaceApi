package com.example.spaceapi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DataShow extends AppCompatActivity  {

    FusedLocationProviderClient mFusedLocationClient;
    int PERMISSION_ID = 44;
    String lt,lo;
    ObjectAdapter objectAdapter;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_show);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        final String id = intent.getStringExtra("id");
        final ArrayList<JSONObject> jsonObjects=new ArrayList<>();

        final ConstraintLayout parent = findViewById(R.id.parent);
        TextView sat_name = findViewById(R.id.sat_name);
        TextView sat_des = findViewById(R.id.sat_des);
        final TextView tle1 = findViewById(R.id.tle1);
        final TextView tle2 = findViewById(R.id.tle2);
        final EditText etSec = findViewById(R.id.etSec);
        Button predict = findViewById(R.id.predict);
        final ProgressBar pgbar = findViewById(R.id.pgbar);
        TextView tvlatitude=findViewById(R.id.latitude);
        final TextView dtlatitude=findViewById(R.id.dtlatitude);
        TextView tvlongitude=findViewById(R.id.longitude);
        final TextView dtlongitude=findViewById(R.id.dtlongitude);
        TextView tvaltitude=findViewById(R.id.altitude);
        TextView dtaltitude=findViewById(R.id.dtaltitute);
        TextView tvazimuth=findViewById(R.id.azimuth);
        final TextView dtazimuth=findViewById(R.id.dtazimuth);
        TextView tvelevation=findViewById(R.id.elevation);
        final TextView dtelevation=findViewById(R.id.dtelevation);
        final TextView dttime=findViewById(R.id.dtTime);
        final CardView card=findViewById(R.id.card);
        final Button map=findViewById(R.id.map);
        final EditText etday=findViewById(R.id.etday);
        final EditText etday2=findViewById(R.id.etday2);
        Button result3=findViewById(R.id.result3);

        Button result=findViewById(R.id.result);
        recyclerView=findViewById(R.id.recycleView);
        recyclerView2=findViewById(R.id.recycleView2);

        final ProgressBar pgbar2 = findViewById(R.id.pgbar2);



        pgbar.setVisibility(View.VISIBLE);
        parent.setAlpha(0.4f);

        sat_name.setText(name);
        sat_des.setText(description);
        final String url = "https://api.n2yo.com/rest/v1/satellite/tle/" + id + "&apiKey=CAQTDG-5MBX8Z-KUYQT4-4SYJ";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        final JsonObjectRequest
                jsonObjectRequest
                = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("ritik", "" + response);
                        JSONObject jo = (JSONObject) response;
                        String tle;
                        try {
                            Log.i("ritik", "" + jo.getString("tle"));

                            tle = jo.getString("tle");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            tle = e.toString();
                        }
                        String lines[] = tle.split("\\r?\\n");

                        tle1.setText(lines[0].substring(2));
                        tle2.setText(lines[1].substring(2));
                        pgbar.setVisibility(View.GONE);
                        parent.setAlpha(1f);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("ritik", "" + error);
                        Toast.makeText(DataShow.this, error.toString(), Toast.LENGTH_LONG).show();
                        pgbar.setVisibility(View.GONE);
                        parent.setAlpha(1f);

                    }
                });
        requestQueue.add(jsonObjectRequest);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        predict.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                pgbar.setVisibility(View.VISIBLE);
            if(etSec.getText().toString()!=null) {


                mFusedLocationClient.getLastLocation()
                        .addOnSuccessListener(DataShow.this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    Log.i("location",""+location);
                                    String latitude= "/"+String.valueOf(location.getLatitude());
                                    String longitude="/"+String.valueOf(location.getLongitude());
                                    String altitude="/"+String.valueOf(location.getAltitude());
                                    String sec="/"+etSec.getText().toString();

                                    RequestQueue queue = Volley.newRequestQueue(DataShow.this);
                                    String url2=" https://api.n2yo.com/rest/v1/satellite/positions/"+id+latitude+longitude+altitude+sec+"/&apiKey=CAQTDG-5MBX8Z-KUYQT4-4SYJ";

                                    JsonObjectRequest
                                            jsonObjectRequest2
                                            = new JsonObjectRequest(
                                            Request.Method.GET,
                                            url2,
                                            null,
                                            new Response.Listener() {
                                                @Override
                                                public void onResponse(Object response) {
                                                    Log.i("mary",""+response);
                                                    JSONObject jo = (JSONObject) response;
                                                    JSONArray array= null;
                                                    try {
                                                        array = jo.getJSONArray("positions");
                                                        Log.i("mary1",""+array);

                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    ArrayList<JSONObject> listdata = new ArrayList<>();
                                                    if (array != null) {
                                                        for (int i=0;i<array.length();i++){
                                                            try {
                                                                listdata.add(array.getJSONObject(i));
                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                        }
                                                    }

                                                    Collections.reverse(listdata);
                                                    JSONObject jo2 = (JSONObject) listdata.get(0);

                                                    try {
                                                        String latitude2=jo2.getString("satlatitude");
                                                        String longitude2=jo2.getString("satlongitude");
                                                        String altitude2=jo2.getString("sataltitude");
                                                        String azimuth=jo2.getString("azimuth");
                                                        String elevation=jo2.getString("elevation");
                                                        String time=jo2.getString("timestamp");
                                                        lt=latitude2;
                                                        lo=longitude2;

                                                        dtlatitude.setText(latitude2);
                                                        dtlatitude.setText(altitude2);
                                                        dtlongitude.setText(longitude2);
                                                        dtazimuth.setText(azimuth);
                                                        dtelevation.setText(elevation);
                                                        dttime.setText(time);
                                                        card.setVisibility(View.VISIBLE);
                                                        pgbar.setVisibility(View.GONE);

                                                        map.setOnClickListener(new View.OnClickListener() {
                                                            @Override
                                                            public void onClick(View view) {
                                                                String geoUri = "http://maps.google.com/maps?q=loc:" + lt + "," + lo + " (" + "" + ")";
                                                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                                                                startActivity(intent);
                                                            }
                                                        });


                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    } }},
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error)
                                                {
                                                    Log.i("mary",""+error);

                                                }
                                            });
                                    queue.add(jsonObjectRequest2);

                                } else {
                                    Log.i("mary", "error");

                                }
                            }
                        }); }
            else{
                Toast.makeText(DataShow.this, "Field Cannot be empty", Toast.LENGTH_SHORT).show();

            }
            }});


        result.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                pgbar.setVisibility(View.VISIBLE);
                if(etday.getText().toString()!=null){
                    mFusedLocationClient.getLastLocation()
                            .addOnSuccessListener(DataShow.this, new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        Log.i("location",""+location);
                                        String latitude= "/"+String.valueOf(location.getLatitude());
                                        String longitude="/"+String.valueOf(location.getLongitude());
                                        String altitude="/"+String.valueOf(location.getAltitude());
                                        String days="/"+etday.getText().toString();

                                        RequestQueue queue = Volley.newRequestQueue(DataShow.this);
                                        String url2=" https://api.n2yo.com/rest/v1/satellite/visualpasses/"+id+latitude+longitude+altitude+days+"/1/&apiKey=CAQTDG-5MBX8Z-KUYQT4-4SYJ";

                                        JsonObjectRequest
                                                jsonObjectRequest2
                                                = new JsonObjectRequest(
                                                Request.Method.GET,
                                                url2,
                                                null,
                                                new Response.Listener() {
                                                    @Override
                                                    public void onResponse(Object response) {
                                                        Log.i("mary",""+response);
                                                        JSONObject jo = (JSONObject) response;
                                                        JSONArray array= null;
                                                        try {
                                                            array = jo.getJSONArray("passes");
                                                            Log.i("mary1",""+array);

                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                        ArrayList<JSONObject> listdata = new ArrayList<>();
                                                        if (array != null) {
                                                            for (int i=0;i<array.length();i++){
                                                                try {
                                                                    listdata.add(array.getJSONObject(i));
                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }
                                                            }
                                                        }
if(listdata.size()>0){
    recyclerView.setLayoutManager(new LinearLayoutManager(DataShow.this));
    objectAdapter =new ObjectAdapter(DataShow.this,listdata);
    recyclerView.setAdapter(objectAdapter);
}
else{
    Toast.makeText(DataShow.this, "No data Available", Toast.LENGTH_SHORT).show();
}

                                                        pgbar.setVisibility(View.GONE);



                                                    }
                                                },
                                                new Response.ErrorListener() {
                                                    @Override
                                                    public void onErrorResponse(VolleyError error)
                                                    {
                                                        Log.i("mary",""+error);

                                                    }
                                                });
                                        queue.add(jsonObjectRequest2);
                                    }
                            }}
                            ); }
                else{
                    Toast.makeText(DataShow.this, "Field Cannot be empty", Toast.LENGTH_SHORT).show();

                }
            }});




        result3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                pgbar.setVisibility(View.VISIBLE);
                if(etday2.getText().toString()!=null) {

                    mFusedLocationClient.getLastLocation()
                            .addOnSuccessListener(DataShow.this, new OnSuccessListener<Location>() {
                                        @Override
                                        public void onSuccess(Location location) {
                                            if (location != null) {
                                                Log.i("location", "" + location);
                                                String latitude = "/" + String.valueOf(location.getLatitude());
                                                String longitude = "/" + String.valueOf(location.getLongitude());
                                                String altitude = "/" + String.valueOf(location.getAltitude());
                                                String days = "/" + etday2.getText().toString();


                                                RequestQueue queue = Volley.newRequestQueue(DataShow.this);
                                                String url2 = " https://api.n2yo.com/rest/v1/satellite/radiopasses/" + id + latitude + longitude + altitude + days + "/0/&apiKey=CAQTDG-5MBX8Z-KUYQT4-4SYJ";

                                                JsonObjectRequest
                                                        jsonObjectRequest2
                                                        = new JsonObjectRequest(
                                                        Request.Method.GET,
                                                        url2,
                                                        null,
                                                        new Response.Listener() {
                                                            @Override
                                                            public void onResponse(Object response) {
                                                                Log.i("mary", "" + response);
                                                                JSONObject jo = (JSONObject) response;
                                                                JSONArray array = null;
                                                                try {
                                                                    array = jo.getJSONArray("passes");
                                                                    Log.i("mary1", "" + array);

                                                                } catch (JSONException e) {
                                                                    e.printStackTrace();
                                                                }
                                                                ArrayList<JSONObject> listdata = new ArrayList<>();
                                                                if (array != null) {
                                                                    for (int i = 0; i < array.length(); i++) {
                                                                        try {
                                                                            listdata.add(array.getJSONObject(i));
                                                                        } catch (JSONException e) {
                                                                            e.printStackTrace();
                                                                        }
                                                                    }
                                                                }

                                                                if(listdata.size()>0) {
                                                                    recyclerView2.setLayoutManager(new LinearLayoutManager(DataShow.this));
                                                                    objectAdapter = new ObjectAdapter(DataShow.this, listdata);
                                                                    recyclerView2.setAdapter(objectAdapter);
                                                                    pgbar.setVisibility(View.GONE);
                                                                }
                                                                else{
                                                                    Toast.makeText(DataShow.this, "No data Available", Toast.LENGTH_SHORT).show();
                                                                }


                                                            }
                                                        },
                                                        new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {
                                                                Log.i("mary", "" + error);

                                                            }
                                                        });
                                                queue.add(jsonObjectRequest2);
                                            }
                                        }
                                    }
                            );
                }
                else
                {
                    Toast.makeText(DataShow.this, "Field Cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }




//    @SuppressLint("MissingPermission")
//    public void radiopass(String id){
//        mFusedLocationClient.getLastLocation()
//                .addOnSuccessListener(DataShow.this, new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        if (location != null) {
//                            Log.i("location",""+location);
//                            String latitude= "/"+String.valueOf(location.getLatitude());
//                            String longitude="/"+String.valueOf(location.getLongitude());
//                            String altitude="/"+String.valueOf(location.getAltitude());
//                            String days="/"+etday2.getText().toString();
//
//
//                            RequestQueue queue = Volley.newRequestQueue(DataShow.this);
//                            String url2=" https://api.n2yo.com/rest/v1/satellite/radiopasses/"+id+latitude+longitude+altitude+days+"/0/&apiKey=CAQTDG-5MBX8Z-KUYQT4-4SYJ";
//
//                            JsonObjectRequest
//                                    jsonObjectRequest2
//                                    = new JsonObjectRequest(
//                                    Request.Method.GET,
//                                    url2,
//                                    null,
//                                    new Response.Listener() {
//                                        @Override
//                                        public void onResponse(Object response) {
//                                            Log.i("mary",""+response);
//                                            JSONObject jo = (JSONObject) response;
//                                            JSONArray array= null;
//                                            try {
//                                                array = jo.getJSONArray("passes");
//                                                Log.i("mary1",""+array);
//
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//                                            ArrayList<JSONObject> listdata = new ArrayList<>();
//                                            if (array != null) {
//                                                for (int i=0;i<array.length();i++){
//                                                    try {
//                                                        listdata.add(array.getJSONObject(i));
//                                                    } catch (JSONException e) {
//                                                        e.printStackTrace();
//                                                    }
//                                                }
//                                            }
//
//                                            recyclerView2.setLayoutManager(new LinearLayoutManager(DataShow.this));
//                                            objectAdapter =new ObjectAdapter(DataShow.this,listdata);
//                                            recyclerView2.setAdapter(objectAdapter);
//
//
//                                        }
//                                    },
//                                    new Response.ErrorListener() {
//                                        @Override
//                                        public void onErrorResponse(VolleyError error)
//                                        {
//                                            Log.i("mary",""+error);
//
//                                        }
//                                    });
//                            queue.add(jsonObjectRequest2);
//                        }
//                    }}
//                ); }


}