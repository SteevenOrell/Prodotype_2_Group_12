package com.example.prototype_1_group_12;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RouteViewModel extends AndroidViewModel {

    public DBRepository mRepository;

    private LiveData<List<Routes>> mAllRoutes;
    public RouteViewModel (Application application){
        super(application);
        mRepository = new DBRepository(application);
        mAllRoutes = updateRouteList();
    }

    LiveData<List<Routes>> getAllRoutes(){
        if(mAllRoutes == null){

        mAllRoutes = updateRouteList();}
    return mAllRoutes;}

    public LiveData<List<Routes>> updateRouteList(){
        return mRepository.getAllRoutes();
    }

    public void insert(Routes route){ mRepository.insert(route); }

    public void delete(Routes route){ mRepository.delete(route); }

    public LiveData<Routes> getRoute(String name){ return mRepository.getRoute(name); }

}
