package com.ufpb.criacsvapigoogle.feign.mapsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Leg {

    @JsonProperty("distance")
    private Distance distance;

    @JsonProperty("duration")
    private Duration duration;

    @JsonProperty("end_address")
    private String endAddress;

    @JsonProperty("end_location")
    private EndLocation endLocation;

    @JsonProperty("start_address")
    private String startAddress;

    @JsonProperty("start_location")
    private StartLocation startLocation;

    @JsonProperty("steps")
    private List<Step> steps;

    public Leg() {
    }

    public Leg(Distance distance, Duration duration, String endAddress, EndLocation endLocation,
               String startAddress, StartLocation startLocation, List<Step> steps) {
        this.distance = distance;
        this.duration = duration;
        this.endAddress = endAddress;
        this.endLocation = endLocation;
        this.startAddress = startAddress;
        this.startLocation = startLocation;
        this.steps = steps;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public EndLocation getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public StartLocation getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
