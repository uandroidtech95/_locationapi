package com.example.utsav.locationapidemo;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.utsav.locationapidemo.adapter.GooglePlaceDataAdapter;
import com.example.utsav.locationapidemo.model.GooglePlaceDataResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecycleActivity extends AppCompatActivity {
    private static final String TAG = "RecycleActivity";
    private double latitude;
    private double longitude;
    private String placeType;
    private String URL;
    private RecyclerView rvGetData;
    private SharedPreferences sharedPreferences;
//    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            latitude = intent.getDoubleExtra("lati", 0);
//            longitude = intent.getDoubleExtra("longi", 0);
//            Log.d(TAG, "values " + latitude + " " + longitude);
//            Toast.makeText(RecycleActivity.this, ""+latitude, Toast.LENGTH_SHORT).show();
//        }
//    };

//    @Override
//    protected void onStart() {
//        super.onStart();
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction("Add");
//        registerReceiver(broadcastReceiver, intentFilter);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        sharedPreferences=getSharedPreferences("latlng",MODE_PRIVATE);

        latitude=sharedPreferences.getLong("lati",0);
        longitude=sharedPreferences.getLong("longi",0);
        if (getIntent() != null) {


            placeType = getIntent().getStringExtra("placetype");

        } else {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
        rvGetData = (RecyclerView) findViewById(R.id.rv_getdata);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecycleActivity.this, LinearLayoutManager.VERTICAL, false);
        rvGetData.setLayoutManager(layoutManager);

        URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latitude + "," + longitude + "&radius=20000&type=" + placeType + "&key=AIzaSyDjT2VD4eHr93BSZjGGwJQdCRYPJ-RFsOs";
        Log.d(TAG,URL);
        PlacesDataTask placesDataTask = new PlacesDataTask();
        placesDataTask.execute(URL);
    }

    private String getJsonData(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();


        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
public GooglePlaceDataResponse GetdatafromGson(String json){
    Gson gson=new Gson();
    GooglePlaceDataResponse googlePlaceDataResponse=gson.fromJson(json,GooglePlaceDataResponse.class);
    return googlePlaceDataResponse;
}
//    @Override
//    protected void onStop() {
//        super.onStop();
//        unregisterReceiver(broadcastReceiver);
//    }

    private class PlacesDataTask extends AsyncTask<String, Void, GooglePlaceDataResponse> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(RecycleActivity.this, "Getting Data", "");
        }

        @Override
        protected GooglePlaceDataResponse doInBackground(String... params) {
            try {
                String json=getJsonData(params[0]);
                return GetdatafromGson(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(GooglePlaceDataResponse googlePlaceDataResponse) {
            super.onPostExecute(googlePlaceDataResponse);
            progressDialog.dismiss();
            if (googlePlaceDataResponse==null){
                Toast.makeText(RecycleActivity.this, "no data", Toast.LENGTH_SHORT).show();
                return;
            }
            GooglePlaceDataAdapter googlePlaceDataAdapter=new GooglePlaceDataAdapter(RecycleActivity.this,googlePlaceDataResponse.getGooglePlacesResultArrayList());
            rvGetData.setAdapter(googlePlaceDataAdapter);

        }
    }
}
