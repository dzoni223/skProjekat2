package com.example.ServisZakazivanje.dto;

import java.util.Map;

public class GymDto {
    private Long id;
    private String name;
    private String address;
    private Map<String, Double> trainingPrices;

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, Double> getTrainingPrices() {
        return trainingPrices;
    }

    public void setTrainingPrices(Map<String, Double> trainingPrices) {
        this.trainingPrices = trainingPrices;
    }
}
