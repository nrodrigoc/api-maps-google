package com.ufpb.criacsvapigoogle.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.ufpb.criacsvapigoogle.dto.PontoCSV;
import com.ufpb.criacsvapigoogle.dto.RotaCSV;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CSVService {

    public void leCSV() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/csv/pontos.csv"));
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

    public void imprimeCsvConsole(List<PontoCSV> pontos) {
        for (PontoCSV ponto : pontos)
            System.out.println(ponto);
    }

    public List<PontoCSV> getListaPontosFromCSV() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/csv/pontos.csv"));

            CsvToBean<PontoCSV> csvToBean = new CsvToBeanBuilder<PontoCSV>(reader).withType(PontoCSV.class).build();
            List<PontoCSV> pontos = csvToBean.parse();

            return pontos;
        }catch (NoSuchFileException e) {
            System.out.println("Não foi encontrado nenhum arquivo com o nome indicado");
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void escreveCSVdePontos(List<PontoCSV> pontos) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("src/main/resources/csv/resultado/pontos-lidos.csv"));
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

            beanToCsv.write(pontos);

            writer.flush();
            writer.close();
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }

    public void escreveCSVdeRotas(List<RotaCSV> rotas) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("src/main/resources/csv/resultado/rotas.csv"));
            StatefulBeanToCsv<RotaCSV> beanToCsv = new StatefulBeanToCsvBuilder<RotaCSV>(writer).build();

            beanToCsv.write(rotas);

            writer.flush();
            writer.close();
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }
    }
}
