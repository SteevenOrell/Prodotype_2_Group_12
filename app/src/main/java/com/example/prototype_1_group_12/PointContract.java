package com.example.prototype_1_group_12;

import android.provider.BaseColumns;

public final class PointContract {

    private PointContract(){}
       public static class PointEntity implements BaseColumns {
        public static final String TABLE_NAME = "Points_table";
        public static final String COLUMN_NAME_ROUTE_ID = "route_id";
        public static final String COLUMN_NAME_LONG = "longitude";
        public static final String COLUMN_NAME_LAT = "latitude";
        public static final String COLUMN_NAME_DATE = "date";


        public static final String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ( "
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME_ROUTE_ID + " INTEGER, "
                + COLUMN_NAME_LONG + " REAL, " + COLUMN_NAME_LAT + " REAL, " + COLUMN_NAME_DATE + " TEXT)";


        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
