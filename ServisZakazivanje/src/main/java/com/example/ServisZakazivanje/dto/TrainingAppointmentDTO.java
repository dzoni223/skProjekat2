package com.example.ServisZakazivanje.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class TrainingAppointmentDTO {
    @JsonProperty("id")
    private Long id;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("startTime")
    private LocalDateTime startTime;

    @JsonProperty("trainingType")
    private String trainingType;

    @JsonProperty("groupTraining")
    private boolean isGroupTraining;
    // other fields, getters, setters

    private Integer maxGroupSize;

    public Integer getMaxGroupSize() {
        return maxGroupSize;
    }

    public void setMaxGroupSize(Integer maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public boolean isGroupTraining() {
        return isGroupTraining;
    }

    public void setGroupTraining(boolean groupTraining) {
        isGroupTraining = groupTraining;
    }
}
