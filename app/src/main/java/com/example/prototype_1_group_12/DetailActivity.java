package com.example.prototype_1_group_12;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    Button btnBackDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView t = findViewById(R.id.txtV_route);
        String routeInfo = getIntent().getExtras().getString(RouteHistoryActivity.KEY);
        t.setText(routeInfo);

        btnBackDetail = findViewById(R.id.btn_backDet);
        btnBackDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
