package com.ufpb.criacsvapigoogle.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.ufpb.criacsvapigoogle.dto.PontoCSV;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class LeitorCSVService {

    public void leCSV() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/csv/rotas.csv"));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> pontos = csvReader.readAll();
            for (String[] ponto : pontos)
                System.out.println("ID : " + ponto[0] +
                        " - Name : " + ponto[1] +
                        " - Lat : " + ponto[2] +
                        " - Long : " + ponto[3] +
                        " - Acessivel : " + ponto[4]
                        );
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

    }

    public void leCSVComoObj() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/csv/rotas.csv"));

            CsvToBean csvToBean = new CsvToBeanBuilder(reader).withType(PontoCSV.class).build();
            List<PontoCSV> pontos = csvToBean.parse();

            for (PontoCSV ponto : pontos)
                System.out.println(ponto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void escreveCSV() {

    }
}
