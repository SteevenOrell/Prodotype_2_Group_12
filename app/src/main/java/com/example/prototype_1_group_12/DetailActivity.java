package com.example.prototype_1_group_12;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    FloatingActionButton fabBack;

    private PointHelper pHelper = null;
    private RouteHelper rHelper = null;
    private double bLat;
    private double bLong;
    private double lat;
    private double lon;
    private double pLat;
    private double pLong;
    private int routeId;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;

    Marker mCurrLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        TextView t = findViewById(R.id.txtV_route);
        String routeInfo = getIntent().getExtras().getString(RouteHistoryActivity.KEY);
        t.setText(routeInfo);

        rHelper = new RouteHelper(this);
        rHelper.getReadableDatabase();

        Cursor cur = DBHelper.getRouteRow(rHelper, routeInfo);
        cur.moveToNext();
        routeId = cur.getInt(cur.getColumnIndexOrThrow(RouteContract.RouteEntity._ID));

        rHelper.close();

        fabBack = findViewById(R.id.fabBack);
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mGoogleMap=googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        pHelper = new PointHelper(this);
        pHelper.getReadableDatabase();

        Cursor cur = DBHelper.getPointsAtSpecificRouteId(pHelper, routeId);
        cur.moveToNext();

        bLat = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LAT));
        bLong = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LONG));

        LatLng latLng = new LatLng(bLat, bLong);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Starting Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mGoogleMap.addMarker(markerOptions);

        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));

        int i = 0;

        while(cur.moveToNext()){
            if (i == 0){
                lat = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LAT));
                lon = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LONG));
                Polyline line = mGoogleMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(bLat, bLong), new LatLng(lat, lon))
                        .width(5)
                        .color(Color.RED));
                pLat = lat;
                pLong = lon;
                i++;
            }else{
                lat = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LAT));
                lon = cur.getDouble(cur.getColumnIndexOrThrow(PointContract.PointEntity.COLUMN_NAME_LONG));
                Polyline line = mGoogleMap.addPolyline(new PolylineOptions()
                        .add(new LatLng(pLat, pLong), new LatLng(lat, lon))
                        .width(5)
                        .color(Color.RED));
                pLat = lat;
                pLong = lon;
            }
        }

        LatLng latLngEnd = new LatLng(lat, lon);
        markerOptions.position(latLngEnd);
        markerOptions.title("Ending Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        mGoogleMap.addMarker(markerOptions);

        pHelper.close();

    }
}
