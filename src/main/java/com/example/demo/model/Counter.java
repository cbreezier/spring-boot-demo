package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Counter {
    private final int value;

    public Counter(int value) {
        this.value = value;
    }

    @JsonProperty("value")
    public int getValue() {
        return value;
    }
}
