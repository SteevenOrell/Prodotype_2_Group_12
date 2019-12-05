package com.example.prototype_1_group_12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper {

    //private RouteHelper routeHelper = null;


    public static Cursor getAllRoutes(RouteHelper routeHelper){
        //routeHelper = new RouteHelper(context);
       // routeHelper.getReadableDatabase();
        SQLiteDatabase db = routeHelper.getReadableDatabase();

        String [] projection = {
                RouteContract.RouteEntity._ID,
                RouteContract.RouteEntity.COLUMN_NAME_ROUTE_NAME,
                RouteContract.RouteEntity.COLUMN_NAME_DESCRIPTION,
                RouteContract.RouteEntity.COLUMN_NAME_RATING,
                RouteContract.RouteEntity.COLUMN_NAME_DATE,
        };
        String selection = null;
        String[] selectionArgs = null;
        return db.query(
                RouteContract.RouteEntity.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

    }

    public static long addRoute(RouteHelper rout,String name,String description,double rating,String date){

        SQLiteDatabase db = rout.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(RouteContract.RouteEntity.COLUMN_NAME_ROUTE_NAME,name);
        cv.put(RouteContract.RouteEntity.COLUMN_NAME_DESCRIPTION,description);
        cv.put(RouteContract.RouteEntity.COLUMN_NAME_RATING,rating);
        cv.put(RouteContract.RouteEntity.COLUMN_NAME_DATE,date);
        return db.insert(RouteContract.RouteEntity.TABLE_NAME,null,cv);

    }

    public static long deleteRoute(RouteHelper rou, String name){

        SQLiteDatabase db = rou.getWritableDatabase();
        String whereClause = " route_name=? ";
        String[]whereArgs = new String[]{name};

        return db.delete(RouteContract.RouteEntity.TABLE_NAME,whereClause,whereArgs);

    }

    //you can get Id of the route by typing the routename
    public static Cursor getRouteRow(RouteHelper routeHelper, String routename){

        SQLiteDatabase db = routeHelper.getReadableDatabase();

        String [] projection = {
                RouteContract.RouteEntity._ID,
                RouteContract.RouteEntity.COLUMN_NAME_ROUTE_NAME,
                RouteContract.RouteEntity.COLUMN_NAME_DESCRIPTION,
                RouteContract.RouteEntity.COLUMN_NAME_RATING,
                RouteContract.RouteEntity.COLUMN_NAME_DATE,
        };
        String selection = " route_name=? ";
        String[] selectionArgs = {routename}; //or new String[]{routename}
        return db.query(
                RouteContract.RouteEntity.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );



    }

//-------------------------------------Methods for points--------------------------------------------------

    public static Cursor getAllPoints(PointHelper pointHelper){

        SQLiteDatabase db = pointHelper.getReadableDatabase();

        String [] projection = {
                PointContract.PointEntity._ID,
                PointContract.PointEntity.COLUMN_NAME_ROUTE_ID,
                PointContract.PointEntity.COLUMN_NAME_LONG,
                PointContract.PointEntity.COLUMN_NAME_LAT,
                PointContract.PointEntity.COLUMN_NAME_DATE,
        };
        String selection = null;
        String[] selectionArgs = null;
        return db.query(
                PointContract.PointEntity.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

    }

    public static long addPoint(PointHelper pointHelper, int route_id, double lon, double lat, String date){

         SQLiteDatabase db = pointHelper.getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put(PointContract.PointEntity.COLUMN_NAME_ROUTE_ID,route_id);
         cv.put(PointContract.PointEntity.COLUMN_NAME_LONG,lon);
         cv.put(PointContract.PointEntity.COLUMN_NAME_LAT,lat);
         cv.put(PointContract.PointEntity.COLUMN_NAME_DATE,date);
         return db.insert(PointContract.PointEntity.TABLE_NAME,null,cv);

    }


    public static Cursor getPointsAtSpecificRouteId(PointHelper pointHelper,int routeId){


        SQLiteDatabase db = pointHelper.getReadableDatabase();

        String [] projection = {
                PointContract.PointEntity._ID,
                PointContract.PointEntity.COLUMN_NAME_ROUTE_ID,
                PointContract.PointEntity.COLUMN_NAME_LONG,
                PointContract.PointEntity.COLUMN_NAME_LAT,
                PointContract.PointEntity.COLUMN_NAME_DATE,
        };
        String selection = " route_id=? ";
        String[] selectionArgs = {Integer.toString(routeId)};      //error can be here
        return db.query(
                PointContract.PointEntity.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );



    }

}
