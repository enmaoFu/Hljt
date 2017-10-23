package com.hljt.app.domain;

/**
 * Created by Administrator on 2017/10/20.
 */

public class MainInit {


    /**
     * lon : 106.281771
     * unit_id : e91e7dbe6bd74210ae951034f98aaff5
     * unit_name : 江津大酒店
     * lat : 29.276107
     * alarm_receiver : 11
     */

    private double lon;
    private String unit_id;
    private String unit_name;
    private double lat;
    private String alarm_receiver;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(String unit_id) {
        this.unit_id = unit_id;
    }

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAlarm_receiver() {
        return alarm_receiver;
    }

    public void setAlarm_receiver(String alarm_receiver) {
        this.alarm_receiver = alarm_receiver;
    }
}
