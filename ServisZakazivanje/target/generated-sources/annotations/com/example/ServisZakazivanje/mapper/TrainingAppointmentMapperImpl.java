package com.example.ServisZakazivanje.mapper;

import com.example.ServisZakazivanje.domain.TrainingAppointment;
import com.example.ServisZakazivanje.dto.TrainingAppointmentDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-17T12:31:58+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class TrainingAppointmentMapperImpl implements TrainingAppointmentMapper {

    @Override
    public TrainingAppointmentDTO entityToDto(TrainingAppointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        TrainingAppointmentDTO trainingAppointmentDTO = new TrainingAppointmentDTO();

        trainingAppointmentDTO.setId( appointment.getId() );
        trainingAppointmentDTO.setStartTime( appointment.getStartTime() );
        trainingAppointmentDTO.setTrainingType( appointment.getTrainingType() );
        trainingAppointmentDTO.setGroupTraining( appointment.isGroupTraining() );

        return trainingAppointmentDTO;
    }

    @Override
    public List<TrainingAppointmentDTO> entitiesToDtos(List<TrainingAppointment> appointments) {
        if ( appointments == null ) {
            return null;
        }

        List<TrainingAppointmentDTO> list = new ArrayList<TrainingAppointmentDTO>( appointments.size() );
        for ( TrainingAppointment trainingAppointment : appointments ) {
            list.add( entityToDto( trainingAppointment ) );
        }

        return list;
    }

    @Override
    public TrainingAppointment dtoToEntity(TrainingAppointmentDTO appointmentDTO) {
        if ( appointmentDTO == null ) {
            return null;
        }

        TrainingAppointment trainingAppointment = new TrainingAppointment();

        trainingAppointment.setId( appointmentDTO.getId() );
        trainingAppointment.setStartTime( appointmentDTO.getStartTime() );
        trainingAppointment.setTrainingType( appointmentDTO.getTrainingType() );
        trainingAppointment.setGroupTraining( appointmentDTO.isGroupTraining() );

        return trainingAppointment;
    }
}
