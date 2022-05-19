package com.ufpb.criacsvapigoogle.feign.mapsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bounds {

    @JsonProperty("northeast")
    private Northeast northeast;

    @JsonProperty("southwest")
    private Southwest southwest;

    public Bounds() {
    }

    public Bounds(Northeast northeast, Southwest southwest) {
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }
}
