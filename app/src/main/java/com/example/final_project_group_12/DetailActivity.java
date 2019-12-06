package com.example.final_project_group_12;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    FloatingActionButton fabBack;

    private PointHelper pHelper = null;
    private RouteHelper rHelper = null;
    private RouteHelper rHelperEdit = null;
    private FloatingActionButton fabSave;
    private TextView route_details;
    private RatingBar ratingBar;
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

        TextView routeT = findViewById(R.id.txtV_route);
        String routeInfo = getIntent().getExtras().getString(RouteHistoryActivity.KEY);
        fabSave = findViewById(R.id.fabSave);
        route_details = findViewById(R.id.txt_routeDetails);
        ratingBar = findViewById(R.id.ratingBar);
        routeT.setText(routeInfo);




        rHelper = new RouteHelper(this);
        rHelper.getReadableDatabase();

        rHelperEdit = new RouteHelper(this);
        rHelperEdit.getWritableDatabase();

        Cursor cur = DBHelper.getRouteRow(rHelper, routeInfo);
        cur.moveToNext();
        routeId = cur.getInt(cur.getColumnIndexOrThrow(RouteContract.RouteEntity._ID));

        rHelper.close();

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String route_str = routeT.getText().toString();
                String detail_str = route_details.getText().toString();
                double rating_double = ratingBar.getRating();
                Log.d("RATING ::"," this is :" + rating_double);
                if(route_str.length()>0 && detail_str.length()>0 && rating_double>=0 && rating_double<=5){

                     DBHelper.editRouteRating(rHelperEdit,route_str,detail_str,rating_double);
                     finish();

                }


            }
        });

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
