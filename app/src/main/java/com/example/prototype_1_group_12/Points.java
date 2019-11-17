package com.example.prototype_1_group_12;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "point_table", foreignKeys = @ForeignKey(entity = Route.class, parentColumns = "route_id", childColumns = "route_id", onDelete = CASCADE))
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
    private Date date;

    public Points(int routeId, double longitude, double latitude, Date date ) {
        this.routeId = routeId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.date = date;
    }

    @NonNull
    public int getPointId() { return id; }

    public void setPointId(@NonNull int id) { this.id = id; }

    @NonNull
    public int getRouteId() { return routeId; }

    public void setRouteId(@NonNull int routeId) { this.routeId = routeId; }

    public double getLongitude() { return longitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }

    public double getLatitude() { return latitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }
}
