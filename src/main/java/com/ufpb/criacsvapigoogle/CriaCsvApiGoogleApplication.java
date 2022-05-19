package com.ufpb.criacsvapigoogle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CriaCsvApiGoogleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CriaCsvApiGoogleApplication.class, args);
    }

}
