package com.example.utsav.locationapidemo.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utsav.locationapidemo.R;
import com.example.utsav.locationapidemo.model.GooglePlacesResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by utsav on 19-05-2017.
 */

public class GooglePlaceDataAdapter extends RecyclerView.Adapter<GooglePlaceDataAdapter.GooglePlaceDataHolder> {
    private Context context;
    private ArrayList<GooglePlacesResult> googlePlacesResultArrayList = new ArrayList<>();

    public GooglePlaceDataAdapter(Context context, ArrayList<GooglePlacesResult> googlePlacesResultArrayList) {
        this.context = context;
        this.googlePlacesResultArrayList = googlePlacesResultArrayList;
    }

    @Override
    public GooglePlaceDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_place_data, parent, false);
        return new GooglePlaceDataHolder(view);
    }

    @Override
    public void onBindViewHolder(GooglePlaceDataHolder holder, int position) {
        GooglePlacesResult placesResult = googlePlacesResultArrayList.get(position);
        holder.tvName.setText(placesResult.getName());
        Picasso.with(context).load(placesResult.getIconUrl()).into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return googlePlacesResultArrayList.size();
    }

    public static class GooglePlaceDataHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivIcon;
        AppCompatTextView tvName;

        public GooglePlaceDataHolder(View itemView) {
            super(itemView);
            ivIcon = (AppCompatImageView) itemView.findViewById(R.id.iv_icon);
            tvName = (AppCompatTextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
