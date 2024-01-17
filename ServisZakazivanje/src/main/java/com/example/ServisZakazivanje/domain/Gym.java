package com.example.ServisZakazivanje.domain;

import jakarta.persistence.*;

import jakarta.persistence.Id;

import java.util.List;
import java.util.Map;


@Entity
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int trainersCount;
    @ElementCollection
    private List<String> availableTrainingTypes;
    @ElementCollection
    private Map<String, Double> trainingPrices;

    public Gym() {
        // Default constructor for JPA
    }

    public Gym(String name, Map<String, Double> trainingPrices) {
        this.name = name;
        this.trainingPrices = trainingPrices;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTrainersCount() {
        return trainersCount;
    }

    public List<String> getAvailableTrainingTypes() {
        return availableTrainingTypes;
    }

    public Map<String, Double> getTrainingPrices() {
        return trainingPrices;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTrainersCount(int trainersCount) {
        this.trainersCount = trainersCount;
    }

    public void setAvailableTrainingTypes(List<String> availableTrainingTypes) {
        this.availableTrainingTypes = availableTrainingTypes;
    }

    public void setTrainingPrices(Map<String, Double> trainingPrices) {
        this.trainingPrices = trainingPrices;
    }
}
