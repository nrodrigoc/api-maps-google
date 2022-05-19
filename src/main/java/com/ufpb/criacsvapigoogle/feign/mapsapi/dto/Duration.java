package com.ufpb.criacsvapigoogle.feign.mapsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Duration {

    @JsonProperty("text")
    private String text;

    @JsonProperty("value")
    private Long value;

    public Duration() {
    }

    public Duration(String text, Long value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
