package com.example.prototype_1_group_12;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModelProvider {

    private DBRepository dbRepository;
    private LiveData<List<Routes>> allRoutes;
    private LiveData<List<Points>> allPoints;

    public ViewModelProvider(Application application){
        super();
        dbRepository = new DBRepository(application);
        allRoutes = dbRepository.getAllRoutes();
        allPoints = dbRepository.getAllPoints();
    }

    LiveData<List<Routes>> getAllRoutes(){return allRoutes;}
    LiveData<List<Points>> getAllPoints(){return allPoints;}

    public void insertRoute(Routes routes) {dbRepository.insert(routes);}
    public void getAllPoint() {dbRepository.getAllPoints();}


}
