package com.example.prototype_1_group_12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class RouteHistoryActivity extends AppCompatActivity {

    public static final String KEY= "KEY";
    ListView lView;
    Button btnAdd;
    Button btnBack;
   // ImageButton btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_history);

        lView = findViewById(R.id.lView);

        btnBack = findViewById(R.id.btnBack);

        //need to add here route info from database example :
        Route r = new Route("St James","12-09-2019","-23e1312","#work",3);
        RouteList.routeArrayList.add(r);
        MyArrayAdapter routeArrayAdapter = new MyArrayAdapter(this,R.layout.route_itemdesign,RouteList.routeArrayList);

        lView.setAdapter(routeArrayAdapter);
        routeArrayAdapter.notifyDataSetChanged();


        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(parent.getContext(),DetailActivity.class);
                String itemRouteN = ((Route)parent.getItemAtPosition(position)).getRname();
                String itemRouteDate = ((Route)parent.getItemAtPosition(position)).getDate();
                String itemRouteGps = ((Route)parent.getItemAtPosition(position)).getGps();
                String itemRouteTags = ((Route)parent.getItemAtPosition(position)).getTags();

                // i.putExtra(KEY, new String[]{itemRouteN,itemRouteDate,itemRouteGps,itemRouteTags});
                i.putExtra(KEY,itemRouteN);
                startActivity(i);

            }
        });




        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
