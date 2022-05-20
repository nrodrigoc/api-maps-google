package com.ufpb.criacsvapigoogle.service;

import com.ufpb.criacsvapigoogle.dto.PontoCSV;
import com.ufpb.criacsvapigoogle.dto.RotaCSV;
import com.ufpb.criacsvapigoogle.feign.GoogleMapsClient;
import com.ufpb.criacsvapigoogle.feign.mapsapi.dto.EndLocation;
import com.ufpb.criacsvapigoogle.feign.mapsapi.dto.Step;
import com.ufpb.criacsvapigoogle.feign.mapsapi.response.RouteResponse;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapsAPIService {

    public static int ID_ATUAL = 1;

    private GoogleMapsClient googleMapsClient;

    private CSVService csvService;

    @Value("${feign-clients.google-maps.key}")
    private String keyGoogleMaps;

    @Autowired
    public void injetaDependencias(GoogleMapsClient googleMapsClient, CSVService csvService) {
        this.googleMapsClient = googleMapsClient;
        this.csvService = csvService;
    }

    public RouteResponse getRotas(String ponto1, String ponto2) {
        return googleMapsClient.getRotaEntreDoisPontos(keyGoogleMaps, ponto1, ponto2);
    }

    public void geraCSVComRotas() {
        List<PontoCSV> pontos = csvService.getListaPontosFromCSV();
        List<RotaCSV> rotas = getRotasFromPontos(pontos);
        csvService.escreveCSVdeRotas(rotas);
    }

    public List<RotaCSV> getRotasFromPontos(List<PontoCSV> pontos) {
        List<RotaCSV> rotas = new ArrayList<>();

        RouteResponse responseAPI;

        // Pega o portao principal
        PontoCSV portaoPrincipal = pontos.get(0);

        // Remove o portao principal do array
        pontos.remove(0);

        for (PontoCSV ponto : pontos) {
            responseAPI = getRotas(getLatitudeLongetude(ponto), getLatitudeLongetude(portaoPrincipal));
            rotas.add(getRotaFromMapsResponse(responseAPI,
                    ponto.getName() + " - " + portaoPrincipal.getName(),
                    ponto.getId(),
                    portaoPrincipal.getId()));
        }

        return rotas;
    }

    public String getLatitudeLongetude(PontoCSV ponto) {
        return "" + ponto.getLat() + ", " + ponto.getLng();
    }


    public RotaCSV getRotaFromMapsResponse(RouteResponse response, String nome, String idInicio, String idFim) {
        RotaCSV rotaCSV = new RotaCSV();

        List<Step> steps = response.getRoutes().get(0).getLegs().get(0).getSteps();

        StringBuilder points = new StringBuilder("[");
        for (Step step : steps) {
            points.append("\"");
            points.append(step.getStartLocation().getLatitude());
            points.append(",");
            points.append(step.getStartLocation().getLongitude());
            points.append("\",");
        }

        // Pega o ponto final
        EndLocation endLocation = steps.get(steps.size()-1).getEndLocation();
        points.append(endLocation.getLatitude());
        points.append(",");
        points.append(endLocation.getLongitude());
        points.append("\"]");

        rotaCSV.setId(String.valueOf(ID_ATUAL));
        rotaCSV.setPoints(points.toString());
        rotaCSV.setName(nome);
        rotaCSV.setIdStart(idInicio);
        rotaCSV.setIdEnd(idFim);

        ID_ATUAL++;
        return rotaCSV;
    }
}
