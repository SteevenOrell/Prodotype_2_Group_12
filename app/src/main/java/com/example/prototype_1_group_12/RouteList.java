package com.example.prototype_1_group_12;

import java.util.ArrayList;

public class RouteList {

    public static ArrayList<Routes> routeArrayList = new ArrayList<>();

    public static void addRoute(Routes r){

        routeArrayList.add(r);

    }

    public static void removeRoute(Routes r){

        routeArrayList.remove(r);
    }

    public static void searchRoute(String routeName){


    }
}
