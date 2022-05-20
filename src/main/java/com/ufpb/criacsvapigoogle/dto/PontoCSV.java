package com.ufpb.criacsvapigoogle.dto;

public class PontoCSV {

    private String id;

    private String name;

    private String lat;

    private String lng;

    private String accessible;

    public PontoCSV() {
    }

    public PontoCSV(String id, String name, String lat, String lng, String accessible) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.accessible = accessible;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAccessible() {
        return accessible;
    }

    public void setAccessible(String accessible) {
        this.accessible = accessible;
    }

    @Override
    public String toString() {
        return "pontoCSV{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", accessible='" + accessible + '\'' +
                '}';
    }
}
