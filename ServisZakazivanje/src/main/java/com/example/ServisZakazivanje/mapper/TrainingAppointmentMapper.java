package com.example.ServisZakazivanje.mapper;

import com.example.ServisZakazivanje.domain.TrainingAppointment;
import com.example.ServisZakazivanje.dto.TrainingAppointmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrainingAppointmentMapper {
    TrainingAppointmentDTO entityToDto(TrainingAppointment appointment);
    @Mapping(target = "id", ignore = true)
    List<TrainingAppointmentDTO> entitiesToDtos(List<TrainingAppointment> appointments);
    TrainingAppointment dtoToEntity(TrainingAppointmentDTO appointmentDTO);
}
