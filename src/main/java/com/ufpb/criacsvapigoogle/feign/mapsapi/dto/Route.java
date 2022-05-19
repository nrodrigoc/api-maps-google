package com.ufpb.criacsvapigoogle.feign.mapsapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Route {

    @JsonProperty("bounds")
    private Bounds bounds;

    @JsonProperty("copyrights")
    private String copyrights;

    @JsonProperty("legs")
    private List<Leg> legs;

    public Route() {
    }

    public Route(Bounds bounds, String copyrights, List<Leg> legs) {
        this.bounds = bounds;
        this.copyrights = copyrights;
        this.legs = legs;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public String getCopyrights() {
        return copyrights;
    }

    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }
}
