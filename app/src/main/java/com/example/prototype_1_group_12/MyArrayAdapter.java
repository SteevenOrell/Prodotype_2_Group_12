package com.example.prototype_1_group_12;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<Routes> {

    private RoutesDAO dao;
    private RoomDatabase db;
  //  private RouteViewModel rvm;
    private int layout;
    public MyArrayAdapter(@NonNull Context context, int resource, @NonNull List<Routes> objects) {
        super(context, resource, objects);
        layout = resource;


    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Routes r = getItem(position);
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layout,null);

        }
        TextView txtName = convertView.findViewById(R.id.txtRouteName);
        TextView txtDate = convertView.findViewById(R.id.txtDate);
        TextView txtDesc = convertView.findViewById(R.id.txtDescription);

        TextView txtRate = convertView.findViewById(R.id.txtRate);
        ImageButton image = convertView.findViewById(R.id.imBtnDel);
        image.setTag(position);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Routes rou = RouteList.routeArrayList.get(position);
                RouteList.routeArrayList.remove(rou);
                 RouteHistoryActivity.routeViewModel.delete(rou);
                //rvm.delete(rou);
                //it should remove from DB here too
                notifyDataSetChanged();

            }
        });

        String rateString = String.valueOf(getItem(position).getRating());
        txtName.setText(getItem(position).getName());
        txtDate.setText(getItem(position).getDate());
        txtDesc.setText(getItem(position).getDesc());

        txtRate.setText(rateString+"/5");

        return convertView;
    }
}
