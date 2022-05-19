package com.ufpb.criacsvapigoogle.service;

import com.ufpb.criacsvapigoogle.feign.GoogleMapsClient;
import com.ufpb.criacsvapigoogle.feign.mapsapi.response.RouteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MapsAPIService {

    private GoogleMapsClient googleMapsClient;

    @Value("${feign-clients.google-maps.key}")
    private String keyGoogleMaps;

    @Autowired
    public void injetaDependencias(GoogleMapsClient googleMapsClient) {
        this.googleMapsClient = googleMapsClient;
    }

    public RouteResponse getRotas(String ponto1, String ponto2) {
        return googleMapsClient.getRotaEntreDoisPontos(keyGoogleMaps, ponto1, ponto2);
    }


}
