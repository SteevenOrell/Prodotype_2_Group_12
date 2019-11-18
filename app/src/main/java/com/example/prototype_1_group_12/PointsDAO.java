package com.example.prototype_1_group_12;

import android.graphics.Point;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

// Points should be able to do the following operations:
//  -Add
//  -Update
//  -Display all points tracked
@Dao
public interface PointsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPoint(Points points);
/*
    // Updates via GoogleMaps API
    @Update
    LiveData<List<Points>> setLongitudeLatitude(double longitude, double latitude);
*/
    // For View Routes, show static routes from point A - B
    @Query("SELECT * FROM point_table WHERE route_id = :id ORDER BY id ASC")
    List<Points> getAllPoints(int id);

    @Query("SELECT * FROM point_table ORDER BY route_id ASC")
    LiveData<List<Points>> displayPoints();

}
