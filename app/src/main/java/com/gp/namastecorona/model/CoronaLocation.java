package com.gp.namastecorona.model;

import androidx.annotation.Nullable;

import net.sharewire.googlemapsclustering.ClusterItem;

public class CoronaLocation implements ClusterItem {

    @Override
    public double getLatitude() {
        return 0;
    }

    @Override
    public double getLongitude() {
        return 0;
    }

    @Nullable
    @Override
    public String getTitle() {
        return null;
    }

    @Nullable
    @Override
    public String getSnippet() {
        return null;
    }
}
