package com.ufpb.criacsvapigoogle.configuration;

import com.ufpb.criacsvapigoogle.exception.handler.CustomErrorDecoderMapsAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapsAPIConfiguration {

    @Bean
    public CustomErrorDecoderMapsAPI customErrorDecoderMapsAPI() {
        return new CustomErrorDecoderMapsAPI();
    }
}
