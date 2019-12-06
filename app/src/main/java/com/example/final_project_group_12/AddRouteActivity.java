package com.example.final_project_group_12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddRouteActivity extends AppCompatActivity {

    private Button saveBtn;
    private TextView routeName;
    private TextView errorMsg;
    public static int STATUS_OK = 1;
    public static int STATUS_NO = 0;
    private RouteHelper rHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        saveBtn = findViewById(R.id.btnSave);
        routeName = findViewById(R.id.editTxtRouteName);
        errorMsg = findViewById(R.id.txtError);
        rHelper = new RouteHelper(this);
        rHelper.getWritableDatabase();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String route_name = routeName.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                String date = sdf.format(new Date());

                if (route_name != null && !route_name.isEmpty()){


                    Intent intent = new Intent();
                    long id = DBHelper.addRoute(rHelper,route_name,null,0,date);
                    intent.putExtra("ROUTE_NAME", route_name);
                    setResult(STATUS_OK , intent);
                    rHelper.close();
                    finish();
                }
                else{
                   errorMsg.setText("Please provide a route name.");
                   errorMsg.setTextColor(Color.RED);
                }
            }
        });


    }
}
