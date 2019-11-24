package com.example.prototype_1_group_12;

import android.content.Context;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Each entity corresponds to a table that will be created in the database
// exportSchema for db migrations | True --> export the schema into a folder | False --> When don't want to keep history of versions (like an in-memory only database).

@Database(entities = {Routes.class, Points.class}, version = 1, exportSchema = false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public abstract RoutesDAO routesDAO();
    public abstract PointsDAO pointsDAO();

    // A singleton To prevent having multiple instances of the db
    private static volatile RoomDatabase INSTANCE;
    private static final int THREADS = 4;
  
    private static androidx.room.RoomDatabase.Callback callback =
            new androidx.room.RoomDatabase.Callback(){

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private RoutesDAO dao;
        PopulateDbAsync(RoomDatabase db){
            this.dao = db.routesDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // dao.deleteAll();
            dao.insert(new Routes("CasaLoma","Work",3,"12-05-2019"));
            dao.insert(new Routes("StJames","Work",3,"12-05-2019"));
            dao.insert(new Routes("Shallowford Road","relax",5,"07-03-2019"));
            dao.insert(new Routes("Dundas St","gym",4,"02-11-2019"));

            return null;
        }
    }
  
    // To run db operations tasks asynchronously
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(THREADS);

    // Creates database the first time it's accessed
    // Returns the singleton
    // Define the db name to rp_database
    static RoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (RoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomDatabase.class, "rp_database").addCallback(callback).build();

                }
            }
        }
        return INSTANCE;
    }
}
