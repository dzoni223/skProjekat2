package com.example.ServisZakazivanje.controller;

import com.example.ServisZakazivanje.domain.TrainingAppointment;
import com.example.ServisZakazivanje.dto.TrainingAppointmentDTO;
import com.example.ServisZakazivanje.mapper.TrainingAppointmentMapper;
import com.example.ServisZakazivanje.security.CheckSecurity;
import com.example.ServisZakazivanje.service.TrainingAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/training-appointments")
public class TrainingAppointmentController {

    private final TrainingAppointmentService trainingAppointmentService;
    private final TrainingAppointmentMapper trainingAppointmentMapper;

    @Autowired
    public TrainingAppointmentController(TrainingAppointmentService trainingAppointmentService,
                                         TrainingAppointmentMapper trainingAppointmentMapper) {
        this.trainingAppointmentService = trainingAppointmentService;
        this.trainingAppointmentMapper = trainingAppointmentMapper;
    }

    @GetMapping
    public ResponseEntity<List<TrainingAppointmentDTO>> getAllTrainingAppointments() {
//        List<TrainingAppointment> appointments = trainingAppointmentService.getAllAppointments();
//        List<TrainingAppointmentDTO> appointmentDTOs = trainingAppointmentMapper.entitiesToDtos(appointments);
        return new ResponseEntity<>(trainingAppointmentService.getAllAppointments(), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"admin"})
    public ResponseEntity<TrainingAppointmentDTO> createTrainingAppointment(@RequestHeader("Authorization") String authorization,
                                                                            @RequestBody TrainingAppointmentDTO appointmentDTO) {
        String jwt = authorization.split(" ")[1];
        System.out.println(jwt);
        Integer userId = trainingAppointmentService.getUserIdFromJwt(jwt);
//        TrainingAppointmentDTO createdAppointment = trainingAppointmentService.scheduleTraining(appointmentDTO);
        return new ResponseEntity<>(trainingAppointmentService.bookTraining(appointmentDTO, userId.longValue()), HttpStatus.CREATED);
    }

//    {
//      "maxGroupSize": 10,
//      "groupTraining": true,
//      "id": 5,
//      "startTime": "2024-09-17T14:40:54.583Z",
//      "trainingType": "kardio"
//    }

    @GetMapping("/filter")
    public ResponseEntity<List<TrainingAppointmentDTO>> filterAppointments(@RequestParam(required = false) String trainingType,
                                                                           @RequestParam(required = false) LocalDateTime localDateTime,
                                                                           @RequestParam(required = false, defaultValue = "ASC") String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "startTime");

        if (trainingType != null) {
            // Both parameters are provided
            List<TrainingAppointmentDTO> filteredAppointments = trainingAppointmentService.getAppointmentsByType(trainingType, sort);
            return new ResponseEntity<>(trainingAppointmentService.getAppointmentsByType(trainingType, sort), HttpStatus.OK);
        } else if (localDateTime != null) {
            // Only localDateTime is provided
            List<TrainingAppointmentDTO> filteredAppointments = trainingAppointmentService.getAppointmentsByDay(localDateTime,sort);
            return new ResponseEntity<>(filteredAppointments, HttpStatus.OK);
        } else {
            // No parameters provided
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}


