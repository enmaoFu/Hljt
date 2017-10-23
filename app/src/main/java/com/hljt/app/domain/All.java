package com.hljt.app.domain;

/**
 * Created by Administrator on 2017/10/20.
 */

public class All {


    /**
     * id : 1
     * lon : 106.258344
     * name : 重庆市江津区自来水公司
     * type : 3
     * lat : 29.287847
     */

    private String id;
    private double lon;
    private String name;
    private String type;
    private double lat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
