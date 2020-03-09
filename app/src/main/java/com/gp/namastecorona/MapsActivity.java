package com.gp.namastecorona;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.gp.namastecorona.model.ConfirmModel;
import com.gp.namastecorona.model.CoronaLocation;

import net.sharewire.googlemapsclustering.Cluster;
import net.sharewire.googlemapsclustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = MapsActivity.class.getSimpleName();
    private ConfirmModel confirmModels = new ConfirmModel();

    private List<ConfirmModel.Location> Locations = new ArrayList<>();
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        confirmModels = getIntent().getParcelableExtra("list");
        type = getIntent().getStringExtra("type");

        getSupportActionBar().setTitle(type + " Location Points");

        Locations = confirmModels.getLocations();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap google) {
        mMap = google;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {

            }
        });

        ClusterManager<CoronaLocation> clusterManager = new ClusterManager<>(this, mMap);
        clusterManager.setCallbacks(new ClusterManager.Callbacks<CoronaLocation>() {
            @Override
            public boolean onClusterClick(@NonNull Cluster<CoronaLocation> cluster) {
                Log.d(TAG, "onClusterClick");
                return false;
            }

            @Override
            public boolean onClusterItemClick(@NonNull CoronaLocation clusterItem) {
                Log.d(TAG, "onClusterItemClick");
                return false;
            }
        });
        mMap.setOnCameraIdleListener(clusterManager);

        List<CoronaLocation> clusterItems = new ArrayList<>();
        for (int i = 0; i < Locations.size(); i++) {
            LatLng latLng = new LatLng(Double.parseDouble(Locations.get(i).getCoordinates().getLat()), Double.parseDouble(Locations.get(i).getCoordinates().getLong()));
            clusterItems.add(new CoronaLocation(latLng, "" + Locations.get(i).getLatest()));//Locations.get(i).getProvince() + "-" +
        }
        clusterManager.setItems(clusterItems);
    }
}
