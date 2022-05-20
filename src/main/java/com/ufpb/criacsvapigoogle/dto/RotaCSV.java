package com.ufpb.criacsvapigoogle.dto;

public class RotaCSV {

    private String id;

    private String name;

    private String idStart;

    private String idEnd;

    private String accessible;

    private String points;

    public RotaCSV() {
    }

    public RotaCSV(String id, String name, String idStart, String idEnd, String accessible, String points) {
        this.id = id;
        this.name = name;
        this.idStart = idStart;
        this.idEnd = idEnd;
        this.accessible = accessible;
        this.points = points;
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

    public String getIdStart() {
        return idStart;
    }

    public void setIdStart(String idStart) {
        this.idStart = idStart;
    }

    public String getIdEnd() {
        return idEnd;
    }

    public void setIdEnd(String idEnd) {
        this.idEnd = idEnd;
    }

    public String getAccessible() {
        return accessible;
    }

    public void setAccessible(String accessible) {
        this.accessible = accessible;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }


}
