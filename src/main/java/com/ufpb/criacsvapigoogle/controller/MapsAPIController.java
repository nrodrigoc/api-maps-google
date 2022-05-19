package com.ufpb.criacsvapigoogle.controller;

import com.ufpb.criacsvapigoogle.feign.mapsapi.response.RouteResponse;
import com.ufpb.criacsvapigoogle.service.MapsAPIService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bomdia")
public class MapsAPIController {

    private final MapsAPIService service;

    public MapsAPIController(MapsAPIService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteResponse> getRotaEntreDoisPontos(@RequestParam("primeiroPonto") String p1, @RequestParam("segundoPonto") String p2){
        return ResponseEntity.ok(service.getRotas(p1, p2));
    }
}
