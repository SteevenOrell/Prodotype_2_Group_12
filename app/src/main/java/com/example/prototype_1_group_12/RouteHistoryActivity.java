package com.example.prototype_1_group_12;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RouteHistoryActivity extends AppCompatActivity {

    public static final String KEY= "KEY";
    ListView lView;
    FloatingActionButton fabBack;
    private RouteHelper rHelper = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_history);

        lView = findViewById(R.id.lView);

        fabBack = findViewById(R.id.fabBack);

        rHelper = new RouteHelper(this);
        rHelper.getReadableDatabase();
        Cursor cursor = DBHelper.getAllRoutes(rHelper);


        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndexOrThrow(RouteContract.RouteEntity.COLUMN_NAME_ROUTE_NAME));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(RouteContract.RouteEntity.COLUMN_NAME_DESCRIPTION));
            float rating = cursor.getFloat(cursor.getColumnIndexOrThrow(RouteContract.RouteEntity.COLUMN_NAME_RATING)); //float can be a problem
            String date = cursor.getString(cursor.getColumnIndexOrThrow(RouteContract.RouteEntity.COLUMN_NAME_DATE));

              Routes r = new Routes(name,description,rating,date);

           RouteList.addRoute(r);
        }
        final  MyArrayAdapter routeArrayAdapter = new MyArrayAdapter(this,R.layout.route_itemdesign,RouteList.routeArrayList);


        lView.setAdapter(routeArrayAdapter);
        routeArrayAdapter.notifyDataSetChanged();
       cursor.close();
        rHelper.close();
        // Setup database controller.
      //  routeViewModel = new ViewModelProvider(this).get(RouteViewModel.class);
      //  routeViewModel.getAllRoutes().observe(this, new Observer<List<Routes>>() {

       //     @Override
       //     public void onChanged(List<Routes> route) {
       //        if(route != null){
        //           RouteList.routeArrayList = route;
         //          final  MyArrayAdapter routeArrayAdapter = new MyArrayAdapter(getApplicationContext(),R.layout.route_itemdesign, RouteList.routeArrayList);
         //          lView.setAdapter(routeArrayAdapter);
        //    }

      //          routeArrayAdapter.notifyDataSetChanged();

       //     }
       // });

    lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(parent.getContext(),DetailActivity.class);
                String itemRouteN = ((Routes)parent.getItemAtPosition(position)).getName();
                // String itemRouteDate = ((Routes)parent.getItemAtPosition(position)).getDate();
                // float itemrate = ((Routes)parent.getItemAtPosition(position)).getRating();
                // String itemDesc = ((Routes)parent.getItemAtPosition(position)).getDesc();

                // i.putExtra(KEY, new String[]{itemRouteN,itemRouteDate,itemRouteGps,itemRouteTags});
                i.putExtra(KEY,itemRouteN);
                startActivity(i);

            }
        });

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        }
}
