package com.example.prototype_1_group_12;

public class Route {

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    private int rate;
    public String getRname() {
        return Rname;
    }

    public void setRname(String rname) {
        Rname = rname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    private String Rname, date,gps,tags;

    public Route(){


    }

    public Route(String Routename, String date, String gps, String tags,int rate){

        Rname = Routename;
        this.date = date;
        this.gps = gps;
        this.tags = tags;
        this.rate = rate;
    }


}
