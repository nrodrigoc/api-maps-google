package com.ufpb.criacsvapigoogle.controller;

import com.ufpb.criacsvapigoogle.dto.PontoCSV;
import com.ufpb.criacsvapigoogle.feign.mapsapi.response.RouteResponse;
import com.ufpb.criacsvapigoogle.service.CSVService;
import com.ufpb.criacsvapigoogle.service.MapsAPIService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MapsAPIController {

    private final MapsAPIService mapsAPIService;
    private final CSVService csvService;

    public MapsAPIController(MapsAPIService mapsAPIService, CSVService csvService) {

        this.mapsAPIService = mapsAPIService;
        this.csvService = csvService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RouteResponse> getRotaEntreDoisPontos(@RequestParam("primeiroPonto") String p1, @RequestParam("segundoPonto") String p2){
        return ResponseEntity.ok(mapsAPIService.getRotas(p1, p2));
    }

    @GetMapping("/csv")
    public ResponseEntity<List<PontoCSV>> getCsv() {
        return ResponseEntity.ok(csvService.getListaPontosFromCSV());
    }

    @PostMapping("/csv")
    public void escreveCsv() {
        mapsAPIService.geraCSVComRotas();
        List<PontoCSV> pontos = csvService.getListaPontosFromCSV();
        csvService.escreveCSVdePontos(pontos);
    }
}
