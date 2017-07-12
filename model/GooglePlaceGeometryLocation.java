package com.example.utsav.locationapidemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by utsav on 19-05-2017.
 */

public class GooglePlaceGeometryLocation implements Parcelable {
    @SerializedName("lat")
    private double latitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @SerializedName("lng")
    private double longitude;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
    }

    public GooglePlaceGeometryLocation() {
    }

    protected GooglePlaceGeometryLocation(Parcel in) {
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
    }

    public static final Parcelable.Creator<GooglePlaceGeometryLocation> CREATOR = new Parcelable.Creator<GooglePlaceGeometryLocation>() {
        @Override
        public GooglePlaceGeometryLocation createFromParcel(Parcel source) {
            return new GooglePlaceGeometryLocation(source);
        }

        @Override
        public GooglePlaceGeometryLocation[] newArray(int size) {
            return new GooglePlaceGeometryLocation[size];
        }
    };
}
