package com.example.ServisZakazivanje.mapper;

import com.example.ServisZakazivanje.domain.Gym;
import com.example.ServisZakazivanje.dto.GymCreateDto;
import com.example.ServisZakazivanje.dto.GymDto;
import com.example.ServisZakazivanje.dto.GymUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GymMapper {

    GymMapper INSTANCE = Mappers.getMapper(GymMapper.class);

    @Mapping(target = "id", ignore = true)
    GymDto gymToGymDTO(Gym gym);

    List<GymDto> entitiesToDtos(List<Gym> gyms);

    Gym createGymDTOToGym(GymCreateDto createGymDTO);

    void updateGymFromDTO(GymUpdateDto updateGymDTO, @MappingTarget Gym gym);
}
