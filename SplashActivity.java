package com.example.utsav.locationapidemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class SplashActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    protected static final String TAG = "MainActivity";
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    protected double mLatitudeLabel;
    protected double mLongitudeLabel;
    protected ProgressBar progressBar;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences=getSharedPreferences("latlng",MODE_PRIVATE);
        final SharedPreferences.Editor editor=sharedPreferences.edit();
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        buildGoogleApiClient();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
//                Intent intentBroadcast = new Intent();
//                intentBroadcast.putExtra("lati", mLatitudeLabel);
//                intentBroadcast.putExtra("longi", mLongitudeLabel);
//                intentBroadcast.setAction("Add");
//                sendBroadcast(intentBroadcast);
                editor.putLong("lati", (long) mLatitudeLabel);
                editor.putLong("longi", (long) mLongitudeLabel);
                editor.apply();
                startActivity(intent);

            }
        }, 5000);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeLabel = mLastLocation.getLatitude();
            mLongitudeLabel = mLastLocation.getLongitude();



        } else {

            Toast.makeText(this, "no connection detected", Toast.LENGTH_LONG).show();
        }

        //new ddnjkj fhdhfkfdh hjdj
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
}
