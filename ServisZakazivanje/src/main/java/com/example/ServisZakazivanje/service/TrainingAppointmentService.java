package com.example.ServisZakazivanje.service;

import com.example.ServisZakazivanje.client.userservice.dto.UserDto;
import com.example.ServisZakazivanje.domain.TrainingAppointment;
import com.example.ServisZakazivanje.dto.TrainingAppointmentDTO;
import com.example.ServisZakazivanje.mapper.TrainingAppointmentMapper;
import com.example.ServisZakazivanje.repository.TrainingAppointmentRepository;
import com.example.ServisZakazivanje.security.service.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrainingAppointmentService {
    private final TrainingAppointmentRepository appointmentRepository;
    private final TrainingAppointmentMapper appointmentMapper;
    private RestTemplate userServiceRestTemplate;
    private TokenService tokenService;

    @Autowired
    public TrainingAppointmentService(TrainingAppointmentRepository appointmentRepository,
                                      TrainingAppointmentMapper appointmentMapper, RestTemplate userServiceRestTemplate,
                                      TokenService tokenService) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.userServiceRestTemplate = userServiceRestTemplate;
        this.tokenService = tokenService;
    }

    public TrainingAppointmentDTO scheduleTraining(TrainingAppointmentDTO appointmentDTO) {
        TrainingAppointment appointment = appointmentMapper.dtoToEntity(appointmentDTO);
        TrainingAppointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.entityToDto(savedAppointment);
    }

    public List<TrainingAppointmentDTO> getAllAppointments() {
        List<TrainingAppointment> appointments = appointmentRepository.findAll();
        return appointmentMapper.entitiesToDtos(appointments);
    }

    public List<TrainingAppointmentDTO> getAppointmentsByType(String trainingType, Sort sort) {
        // Implement logic to filter appointments by type
        List<TrainingAppointment> filteredAppointments = appointmentRepository.findByTrainingType(trainingType, sort);
        return appointmentMapper.entitiesToDtos(filteredAppointments);
    }

    public List<TrainingAppointmentDTO> getAppointmentsByDay(LocalDateTime dateTime, Sort sort) {
        // Implement logic to filter appointments by day
        List<TrainingAppointment> filteredAppointments = appointmentRepository.findByStartTimeAfter(dateTime, sort);
        return appointmentMapper.entitiesToDtos(filteredAppointments);
    }

    // Method for booking training appointments
    @Retryable(
            //value = { Messa },
            maxAttempts = 3, // Adjust the number of retries as needed
            backoff = @Backoff(delay = 1000)) // Backoff delay between retries in milliseconds
    public TrainingAppointmentDTO bookTraining(TrainingAppointmentDTO appointment, Long userId) {

        TrainingAppointment appointmentnew = appointmentMapper.dtoToEntity(appointment);
        // Check the type of training
        if (appointment.isGroupTraining()) {
            // Check if there is room for more participants
            if (getParticipantsCount(appointmentnew) < appointment.getMaxGroupSize()) {
                // Book the group training
                // Add the user to the list of participants, update the database, etc.
                ResponseEntity<UserDto> userDtoResponseEntity = userServiceRestTemplate.exchange("/user" + userId, HttpMethod.GET, null, UserDto.class);
                appointmentnew.getClientIds().add(userDtoResponseEntity.getBody().getId());
                //appointment.getClientIds().add(userId); --------
                //userServiceRestTemplate.exchange()
                // Update the database or repository
                // Example: appointmentRepository.save(appointment);
                return appointmentMapper.entityToDto(appointmentnew);
            } else {
                // Handle group training fully booked
                return null;
            }
        } else {
            // Check if there is no existing appointment at the specified time
            if (getAppointmentsByDay(appointment.getStartTime(), Sort.by("ASC")).isEmpty()) {
                // Book the individual training
                // Set the user as the appointment's client, update the database, etc.
                ResponseEntity<UserDto> userDtoResponseEntity = userServiceRestTemplate.exchange("/user" + userId, HttpMethod.GET, null, UserDto.class);
                appointmentnew.getClientIds().add(userDtoResponseEntity.getBody().getId());
                // Update the database or repository
                // Example: appointmentRepository.save(appointment);
                return appointmentMapper.entityToDto(appointmentnew);
            } else {
                // Handle individual training slot already booked
                return null;
            }
        }
    }

    // Method to get the count of participants in a group training
    private int getParticipantsCount(TrainingAppointment appointment) {
        // Implement logic to count participants
        return appointment.getClientIds().size();
    }

    public Integer getUserIdFromJwt(String jwt) {
        Claims claims = tokenService.parseToken(jwt);
        return claims.get("id", Integer.class);
    }

}

