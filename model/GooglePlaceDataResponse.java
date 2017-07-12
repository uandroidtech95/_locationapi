package com.example.utsav.locationapidemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by utsav on 19-05-2017.
 */

public class GooglePlaceDataResponse implements Parcelable {
    public ArrayList<GooglePlacesResult> getGooglePlacesResultArrayList() {
        return googlePlacesResultArrayList;
    }

    public void setGooglePlacesResultArrayList(ArrayList<GooglePlacesResult> googlePlacesResultArrayList) {
        this.googlePlacesResultArrayList = googlePlacesResultArrayList;
    }

    @SerializedName("results")

    ArrayList<GooglePlacesResult> googlePlacesResultArrayList = new ArrayList<>();

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.googlePlacesResultArrayList);
    }

    public GooglePlaceDataResponse() {
    }

    protected GooglePlaceDataResponse(Parcel in) {
        this.googlePlacesResultArrayList = new ArrayList<GooglePlacesResult>();
        in.readList(this.googlePlacesResultArrayList, GooglePlacesResult.class.getClassLoader());
    }

    public static final Parcelable.Creator<GooglePlaceDataResponse> CREATOR = new Parcelable.Creator<GooglePlaceDataResponse>() {
        @Override
        public GooglePlaceDataResponse createFromParcel(Parcel source) {
            return new GooglePlaceDataResponse(source);
        }

        @Override
        public GooglePlaceDataResponse[] newArray(int size) {
            return new GooglePlaceDataResponse[size];
        }
    };
}
