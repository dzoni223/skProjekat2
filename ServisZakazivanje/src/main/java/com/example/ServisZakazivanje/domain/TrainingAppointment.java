package com.example.ServisZakazivanje.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class TrainingAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String trainingType;
    private boolean isGroupTraining;
    private Integer maxGroupSize;
    private Long gymId;
    @ElementCollection
    private List<Long> clientIds;

    public TrainingAppointment() {
        // Default constructor for JPA and serialization
    }

    public TrainingAppointment(Long id, LocalDateTime startTime, LocalDateTime endTime, String trainingType, boolean isGroupTraining, Long gymId, Long clientId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainingType = trainingType;
        this.isGroupTraining = isGroupTraining;
        this.gymId = gymId;
    }

    public Integer getMaxGroupSize() {
        return maxGroupSize;
    }

    public void setMaxGroupSize(Integer maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public boolean isGroupTraining() {
        return isGroupTraining;
    }

    public Long getGymId() {
        return gymId;
    }

    public List<Long> getClientIds() {
        return clientIds;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public void setGroupTraining(boolean groupTraining) {
        isGroupTraining = groupTraining;
    }

    public void setGymId(Long gymId) {
        this.gymId = gymId;
    }

    public void setClientId(Long clientId) {
        this.clientIds = clientIds;
    }
}
