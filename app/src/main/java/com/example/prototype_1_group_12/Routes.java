package com.example.prototype_1_group_12;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

// One to many relationship
//  -Define its relationship to Routes
//  -ForeignKey allows to specify constraints

@Entity(tableName = "route_table")
public class Routes {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "route_id")  // Allows specific customization about the column
    private int route_id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "desc")
    private String desc;

    @ColumnInfo(name = "rating")
    private float rating;

    @ColumnInfo(name = "date")
    private String date;

    public Routes(String name, String desc,float rating, String date) {
        this.name = name;
        this.desc = desc;
        this.rating = rating;
        this.date = date;
    }

    public int getRouteId() {
        return route_id;
    }

    public void setRouteId(int route_id) {
        this.route_id = route_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
