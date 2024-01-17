package com.example.ServisZakazivanje.repository;

import com.example.ServisZakazivanje.domain.TrainingAppointment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TrainingAppointmentRepository extends JpaRepository<TrainingAppointment, Long> {
    // Dodajte potrebne metode
    List<TrainingAppointment> findByTrainingType(String trainingType, Sort sort);

    List<TrainingAppointment> findByStartTimeAfter(LocalDateTime startTime, Sort sort);

}

