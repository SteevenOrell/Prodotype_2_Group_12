package com.example.prototype_1_group_12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddRouteActivity extends AppCompatActivity {

    private Button saveBtn;
    private TextView routeName;
    private TextView errorMsg;
    public static int STATUS_OK = 1;
    public static int STATUS_NO = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);

        saveBtn = findViewById(R.id.btnSave);
        routeName = findViewById(R.id.editTxtRouteName);
        errorMsg = findViewById(R.id.txtError);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String route_name = routeName.getText().toString();
                if (route_name != null && !route_name.isEmpty()){
                    Intent intent = new Intent();
                    intent.putExtra("ROUTE_NAME", route_name);
                    setResult(STATUS_OK , intent);
                    finish();
                }
                else{
                    errorMsg.setText("Please fill in all values");
                }
            }
        });


    }
}
