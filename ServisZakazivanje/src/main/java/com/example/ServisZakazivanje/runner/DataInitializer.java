package com.example.ServisZakazivanje.runner;

import com.example.ServisZakazivanje.domain.Gym;
import com.example.ServisZakazivanje.domain.TrainingAppointment;
import com.example.ServisZakazivanje.repository.GymRepository;
import com.example.ServisZakazivanje.repository.TrainingAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {

    private final GymRepository gymRepository;
    private final TrainingAppointmentRepository appointmentRepository;


    @Autowired
    public DataInitializer(GymRepository gymRepository, TrainingAppointmentRepository appointmentRepository) {
        this.gymRepository = gymRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Your code to initialize the database with sample data
        Gym gym1 = new Gym("Gym 1", getTrainingPricesMap(50.0));
        Gym gym2 = new Gym("Gym 2", getTrainingPricesMap(60.0));

        gymRepository.saveAll(List.of(gym1, gym2));

        TrainingAppointment t1 = new TrainingAppointment(null, LocalDateTime.parse("2024-01-17T10:30:00"), LocalDateTime.parse("2024-01-17T12:00:00"), "kardio", true, 12L, null);
        TrainingAppointment t2 = new TrainingAppointment(null, LocalDateTime.parse("2024-01-18T16:30:00"), LocalDateTime.parse("2024-01-18T18:00:00"), "teretana", true, 10L, null);
        TrainingAppointment t3 = new TrainingAppointment(null, LocalDateTime.parse("2024-01-19T11:00:00"), LocalDateTime.parse("2024-01-19T12:30:00"), "kardio", false, null, null);

        appointmentRepository.saveAll(List.of(t1,t2,t3));
    }

    private Map<String, Double> getTrainingPricesMap(Double price) {
        Map<String, Double> trainingPrices = new HashMap<>();
        trainingPrices.put("Basic", price);
        trainingPrices.put("Premium", price * 1.5); // Adjust as needed
        // Add more entries as needed
        return trainingPrices;
    }
}

