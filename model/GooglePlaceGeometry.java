package com.example.utsav.locationapidemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by utsav on 19-05-2017.
 */

public class GooglePlaceGeometry implements Parcelable {
    public GooglePlaceGeometryLocation getGooglePlaceGeometryLocation() {
        return googlePlaceGeometryLocation;
    }

    public void setGooglePlaceGeometryLocation(GooglePlaceGeometryLocation googlePlaceGeometryLocation) {
        this.googlePlaceGeometryLocation = googlePlaceGeometryLocation;
    }

    @SerializedName("location")

    private GooglePlaceGeometryLocation googlePlaceGeometryLocation;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.googlePlaceGeometryLocation, flags);
    }

    public GooglePlaceGeometry() {
    }

    protected GooglePlaceGeometry(Parcel in) {
        this.googlePlaceGeometryLocation = in.readParcelable(GooglePlaceGeometryLocation.class.getClassLoader());
    }

    public static final Parcelable.Creator<GooglePlaceGeometry> CREATOR = new Parcelable.Creator<GooglePlaceGeometry>() {
        @Override
        public GooglePlaceGeometry createFromParcel(Parcel source) {
            return new GooglePlaceGeometry(source);
        }

        @Override
        public GooglePlaceGeometry[] newArray(int size) {
            return new GooglePlaceGeometry[size];
        }
    };
}
