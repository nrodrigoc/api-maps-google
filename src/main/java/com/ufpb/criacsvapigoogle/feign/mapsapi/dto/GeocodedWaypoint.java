package com.ufpb.criacsvapigoogle.feign.mapsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GeocodedWaypoint {

    @JsonProperty("geocoder_status")
    private String geocoderStatus;

    @JsonProperty("place_id")
    private String placeId;

    @JsonProperty("types")
    private List<String> types;

    public GeocodedWaypoint() {
    }

    public GeocodedWaypoint(String geocoderStatus, String placeId, List<String> types) {
        this.geocoderStatus = geocoderStatus;
        this.placeId = placeId;
        this.types = types;
    }

    public String getGeocoderStatus() {
        return geocoderStatus;
    }

    public void setGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
