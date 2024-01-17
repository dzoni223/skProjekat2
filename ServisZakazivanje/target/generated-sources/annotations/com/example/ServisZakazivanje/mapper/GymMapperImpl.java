package com.example.ServisZakazivanje.mapper;

import com.example.ServisZakazivanje.domain.Gym;
import com.example.ServisZakazivanje.dto.GymCreateDto;
import com.example.ServisZakazivanje.dto.GymDto;
import com.example.ServisZakazivanje.dto.GymUpdateDto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-17T12:31:58+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class GymMapperImpl implements GymMapper {

    @Override
    public GymDto gymToGymDTO(Gym gym) {
        if ( gym == null ) {
            return null;
        }

        GymDto gymDto = new GymDto();

        gymDto.setName( gym.getName() );
        Map<String, Double> map = gym.getTrainingPrices();
        if ( map != null ) {
            gymDto.setTrainingPrices( new LinkedHashMap<String, Double>( map ) );
        }

        return gymDto;
    }

    @Override
    public List<GymDto> entitiesToDtos(List<Gym> gyms) {
        if ( gyms == null ) {
            return null;
        }

        List<GymDto> list = new ArrayList<GymDto>( gyms.size() );
        for ( Gym gym : gyms ) {
            list.add( gymToGymDTO( gym ) );
        }

        return list;
    }

    @Override
    public Gym createGymDTOToGym(GymCreateDto createGymDTO) {
        if ( createGymDTO == null ) {
            return null;
        }

        Gym gym = new Gym();

        gym.setName( createGymDTO.getName() );
        Map<String, Double> map = createGymDTO.getTrainingPrices();
        if ( map != null ) {
            gym.setTrainingPrices( new LinkedHashMap<String, Double>( map ) );
        }

        return gym;
    }

    @Override
    public void updateGymFromDTO(GymUpdateDto updateGymDTO, Gym gym) {
        if ( updateGymDTO == null ) {
            return;
        }

        gym.setName( updateGymDTO.getName() );
        if ( gym.getTrainingPrices() != null ) {
            Map<String, Double> map = updateGymDTO.getTrainingPrices();
            if ( map != null ) {
                gym.getTrainingPrices().clear();
                gym.getTrainingPrices().putAll( map );
            }
            else {
                gym.setTrainingPrices( null );
            }
        }
        else {
            Map<String, Double> map = updateGymDTO.getTrainingPrices();
            if ( map != null ) {
                gym.setTrainingPrices( new LinkedHashMap<String, Double>( map ) );
            }
        }
    }
}
