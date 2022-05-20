package com.ufpb.criacsvapigoogle.feign.mapsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Step {

    @JsonProperty("start_location")
    private StartLocation startLocation;

    @JsonProperty("end_location")
    private EndLocation endLocation;

    public Step(StartLocation startLocation, EndLocation endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    public StartLocation getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(StartLocation startLocation) {
        this.startLocation = startLocation;
    }

    public EndLocation getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(EndLocation endLocation) {
        this.endLocation = endLocation;
    }
}
