package com.example.project_mcs_lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    Double longitude = 0.0, latitude = 0.0;
    String name = "";
    Integer userId = 0 , productId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Bundle bundle = getIntent().getExtras();
        userId = bundle.getInt("USER_ID");
        productId = bundle.getInt("PRODUCT_ID");
        longitude = bundle.getDouble("LONGITUDE");
        latitude = bundle.getDouble("LATITUDE");
        name = bundle.getString("NAME");

        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
        supportMapFragment.getMapAsync(this);
        getSupportFragmentManager().beginTransaction().add(R.id.google_map, supportMapFragment).commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng location = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(location)
                .title(name)
                .draggable(true);
        map.addMarker(markerOptions).showInfoWindow();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
    }

    public void backToProductDetail(View view) {
        Intent intent = new Intent(this, ProductDetailPage.class);
        intent.putExtra("idproduct", productId);
        intent.putExtra("iduser", userId);
        startActivity(intent);
        finish();
    }
}