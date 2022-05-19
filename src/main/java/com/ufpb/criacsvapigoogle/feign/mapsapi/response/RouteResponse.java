package com.ufpb.criacsvapigoogle.feign.mapsapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufpb.criacsvapigoogle.feign.mapsapi.dto.GeocodedWaypoint;
import com.ufpb.criacsvapigoogle.feign.mapsapi.dto.Route;

import java.util.List;

public class RouteResponse {

    @JsonProperty("geocoded_waypoints")
    private List<GeocodedWaypoint> geocodedWaypoints;

    @JsonProperty("routes")
    private List<Route> routes;
}
