package com.example.utsav.locationapidemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by utsav on 19-05-2017.
 */

public class GooglePlacesResult implements Parcelable {

    @SerializedName("icon")
    private String iconUrl;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GooglePlaceGeometry getGooglePlaceGeometry() {
        return googlePlaceGeometry;
    }

    public void setGooglePlaceGeometry(GooglePlaceGeometry googlePlaceGeometry) {
        this.googlePlaceGeometry = googlePlaceGeometry;
    }

    @SerializedName("name")

    private String name;
    @SerializedName("rating")
    private float rating;
    @SerializedName("vicinity")
    private String address;
    @SerializedName("geometry")
    private GooglePlaceGeometry googlePlaceGeometry;

    public GooglePlacesResult() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.iconUrl);
        dest.writeString(this.name);
        dest.writeFloat(this.rating);
        dest.writeString(this.address);
        dest.writeParcelable(this.googlePlaceGeometry, flags);
    }

    protected GooglePlacesResult(Parcel in) {
        this.iconUrl = in.readString();
        this.name = in.readString();
        this.rating = in.readFloat();
        this.address = in.readString();
        this.googlePlaceGeometry = in.readParcelable(GooglePlaceGeometry.class.getClassLoader());
    }

    public static final Creator<GooglePlacesResult> CREATOR = new Creator<GooglePlacesResult>() {
        @Override
        public GooglePlacesResult createFromParcel(Parcel source) {
            return new GooglePlacesResult(source);
        }

        @Override
        public GooglePlacesResult[] newArray(int size) {
            return new GooglePlacesResult[size];
        }
    };
}
