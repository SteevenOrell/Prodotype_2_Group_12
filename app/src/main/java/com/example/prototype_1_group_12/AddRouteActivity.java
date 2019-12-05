package com.example.prototype_1_group_12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        EditText desc = findViewById(R.id.editTxtDesc);
        EditText rate = findViewById(R.id.editTxtRatings);
        EditText date = findViewById(R.id.editTxtDate);
        errorMsg = findViewById(R.id.txtError);
        rHelper = new RouteHelper(this);
        rHelper.getWritableDatabase();


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String route_name = routeName.getText().toString();
                String desc_str = desc.getText().toString();
                String rate_str = rate.getText().toString();
                String date_str = date.getText().toString();

                if (route_name != null && !route_name.isEmpty()){
                    Intent intent = new Intent();
                    double rate_double = Double.valueOf(rate_str);
                    long id = DBHelper.addRoute(rHelper,route_name,desc_str,rate_double,date_str);
                    intent.putExtra("ROUTE_NAME", route_name);
                    setResult(STATUS_OK , intent);
                    rHelper.close();
                    finish();
                }
                else{
                   errorMsg.setText("Please fill in all values");
                }
            }
        });


    }
}
