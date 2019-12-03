package com.example.prototype_1_group_12;

// not sure about number of repos - https://stackoverflow.com/questions/53922862/single-on-multiple-dao-and-repository-in-android-room-database-project/53939077

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.Date;
import java.util.List;

// Repository allows access to multiple data sources
// Determines whether to fetch local or network data

public class DBRepository {

    // Routes
    private RoutesDAO routesDAO;
    private LiveData<List<Routes>> allRoutes;

    // Points
    private PointsDAO pointsDAO;
    private LiveData<List<Points>> allPoints;


    DBRepository(Application application){
        RoomDatabase roomDatabase = RoomDatabase.getDatabase(application);

        routesDAO = roomDatabase.routesDAO();
        allRoutes = routesDAO.displayRoutes();

        pointsDAO = roomDatabase.pointsDAO();
        allPoints = pointsDAO.displayPoints();

    }

    // When route has been modified (name, description), LiveDate will notify the observer that the data has changed
    LiveData<List<Routes>> getAllRoutes(){
        return allRoutes;
    }
    LiveData<List<Points>> getAllPoints(){
        return allPoints;
    }

    // ExecutorService manages terimnation and methods
    // Tracking progress for asynchronous tasks

    // ---------- RouteDAO Methods ----------

    void insert(Routes routes){
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            routesDAO.insert(routes);
        });
    }

    void delete(Routes routes){
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            routesDAO.deleteRoute(routes);
        });
    }

    void edit(Routes routes){
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            routesDAO.editRoute(routes);
        });
    }

    // Get route by providing name.
    public LiveData<List<Routes>> getRouteId(String name){
        return routesDAO.getRouteId(name);
    }
/*
    Date setDate(String date){
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            routesDAO.setDate(date);
        });
        return date;
    }
*/
    // ---------- PointsDao Methods ----------

    List<Points> getAllPoints(int route_id){
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            pointsDAO.getAllPoints(route_id);
        });
        return pointsDAO.getAllPoints(route_id);
    }

    void insertPoint(Points points){
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            pointsDAO.insertPoint(points);
        });
    }
/*
    LiveData<List<Points>> setPoints(int lon, int lat){

        RoomDatabase.databaseWriteExecutor.execute(() -> {
            pointsDAO.setLongitudeLatitude(lon, lat);
        });
        return  pointsDAO.setLongitudeLatitude(lon, lat);
    }
*/
}