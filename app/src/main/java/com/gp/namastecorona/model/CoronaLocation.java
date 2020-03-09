package com.gp.namastecorona.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;

import net.sharewire.googlemapsclustering.ClusterItem;

public class CoronaLocation implements ClusterItem {

    private LatLng location;
    private String title_count;

    public CoronaLocation(@NonNull LatLng location, @NonNull String title_count) {
        this.location = location;
        this.title_count = title_count;
    }

    @Override
    public double getLatitude() {
        return location.latitude;
    }

    @Override
    public double getLongitude() {
        return location.longitude;
    }

    @Nullable
    @Override
    public String getTitle() {
        return title_count;
    }

    @Nullable
    @Override
    public String getSnippet() {
        return null;
    }
}
