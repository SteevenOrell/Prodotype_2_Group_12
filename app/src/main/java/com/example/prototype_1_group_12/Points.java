package com.example.prototype_1_group_12;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
// , indices = {@Index("route_id")}
@Entity(tableName = "point_table", foreignKeys = @ForeignKey(entity = Routes.class, parentColumns = "route_id", childColumns = "route_id", onDelete = CASCADE))
public class Points {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "route_id")
    private int routeId;

    @ColumnInfo(name = "long")
    private double longitude;

    @ColumnInfo(name = "lat")
    private double latitude;

    @ColumnInfo(name = "date")
    private String date;

    public Points(int routeId, double longitude, double latitude, String date) {
        this.routeId = routeId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }

    @NonNull
    public int getId() { return id; }

    public void setId(@NonNull int id) { this.id = id; }

    @NonNull
    public int getRouteId() { return routeId; }

    public void setRouteId(@NonNull int routeId) { this.routeId = routeId; }

    public double getLongitude() { return longitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getLatitude() { return latitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
}
