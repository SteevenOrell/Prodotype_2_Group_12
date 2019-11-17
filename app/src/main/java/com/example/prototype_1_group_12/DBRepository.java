package com.example.prototype_1_group_12;

// not sure about number of repos - https://stackoverflow.com/questions/53922862/single-on-multiple-dao-and-repository-in-android-room-database-project/53939077

import android.app.Application;

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

    DBRepository(Application application){
        RoomDatabase roomDatabase = RoomDatabase.getDatabase(application);

        routesDAO = roomDatabase.routesDAO();
        allRoutes = routesDAO.displayRoutes();

        pointsDAO = roomDatabase.pointsDAO();
    }

    // When route has been modified (name, description), LiveDate will notify the observer that the data has changed
    LiveData<List<Routes>> getAllRoutes(){
        return allRoutes;
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

    String getRoute(String name){
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            routesDAO.getRoute(name);
        });
        return name;
    }

    Date setDate(Date date){
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            routesDAO.setDate(date);
        });
        return date;
    }

    // ---------- PointsDao Methods ----------

    List<Points> getAllPoints(Points points){
        RoomDatabase.databaseWriteExecutor.execute(() -> {
            pointsDAO.getPoints(points);
        });
        return pointsDAO.getPoints(points);
    }

    LiveData<List<Points>> setPoints(int lon, int lat){

        RoomDatabase.databaseWriteExecutor.execute(() -> {
            pointsDAO.setLongitudeLatitude(lon, lat);
        });
        return  pointsDAO.setLongitudeLatitude(lon, lat);
    }
}
