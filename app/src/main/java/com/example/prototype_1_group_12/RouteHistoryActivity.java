package com.example.prototype_1_group_12;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_history);

        lView = findViewById(R.id.lView);

        fabBack = findViewById(R.id.fabBack);
        final  MyArrayAdapter routeArrayAdapter = new MyArrayAdapter(this,R.layout.route_itemdesign,RouteList.routeArrayList);

        lView.setAdapter(routeArrayAdapter);

        // Setup database controller.

        }
}
