package com.ufpb.criacsvapigoogle.feign;

import com.ufpb.criacsvapigoogle.configuration.MapsAPIConfiguration;
import com.ufpb.criacsvapigoogle.feign.mapsapi.response.RouteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${feign-clients.google-maps.name}", url="${feign-clients.google-maps.url}", configuration = MapsAPIConfiguration.class)
public interface GoogleMapsClient {

    @GetMapping(path = "/maps/api/directions/json", consumes = "application/json", produces = "application/json")
    RouteResponse getRotaEntreDoisPontos(
            @RequestParam("key") String key,
            @RequestParam("origin") String origem,
            @RequestParam("destination") String destino
    );
}
