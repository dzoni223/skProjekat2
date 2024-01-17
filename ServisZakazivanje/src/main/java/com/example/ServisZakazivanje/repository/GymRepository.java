package com.example.ServisZakazivanje.repository;

import com.example.ServisZakazivanje.domain.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
    // Find gym by name
    Gym findByName(String name);

    // Find gyms by trainers count
    List<Gym> findByTrainersCount(int trainersCount);

    // Find gyms by available training type
    List<Gym> findByAvailableTrainingTypesContaining(String trainingType);

}
