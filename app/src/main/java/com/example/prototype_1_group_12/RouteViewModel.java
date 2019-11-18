package com.example.prototype_1_group_12;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RouteViewModel extends AndroidViewModel {

    private DBRepository mRepository;

    private LiveData<List<Routes>> mAllRoutes;
    public RouteViewModel (Application application){
        super(application);
        mRepository = new DBRepository(application);
        mAllRoutes = mRepository.getAllRoutes();

    }
    LiveData<List<Routes>> getAllRoutes(){return mAllRoutes;}
    public void insert(Routes route){ mRepository.insert(route);}

    public void delete(Routes route){mRepository.delete(route);}

}
