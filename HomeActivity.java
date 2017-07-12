package com.example.utsav.locationapidemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.utsav.locationapidemo.model.GooglePlaceDataResponse;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private AppCompatImageButton ibtnPolice, ibtnAtm, ibtnRestaurant, ibtnHospital;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ibtnAtm = (AppCompatImageButton) findViewById(R.id.ibtn_atm);
        ibtnHospital = (AppCompatImageButton) findViewById(R.id.ibtn_hospital);
        ibtnPolice = (AppCompatImageButton) findViewById(R.id.ibtn_police);
        ibtnRestaurant = (AppCompatImageButton) findViewById(R.id.ibtn_resturant);
        intent = new Intent(HomeActivity.this, RecycleActivity.class);

        ibtnAtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("placetype", "atm");
                startActivity(intent);
            }
        });
        ibtnHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("placetype", "hospital");
                startActivity(intent);
            }
        });
        ibtnPolice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("placetype", "police");
                startActivity(intent);
            }
        });
        ibtnRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("placetype", "restaurant");
                startActivity(intent);
            }
        });
    }


}
