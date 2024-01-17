package com.example.ServisZakazivanje.service;

import com.example.ServisZakazivanje.domain.Gym;
import com.example.ServisZakazivanje.dto.GymCreateDto;
import com.example.ServisZakazivanje.dto.GymDto;
import com.example.ServisZakazivanje.dto.GymUpdateDto;
import com.example.ServisZakazivanje.mapper.GymMapper;
import com.example.ServisZakazivanje.repository.GymRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymService {
    private final GymRepository gymRepository;
    private final GymMapper gymMapper;

    @Autowired
    public GymService(GymRepository gymRepository, GymMapper gymMapper) {
        this.gymRepository = gymRepository;
        this.gymMapper = gymMapper;
    }

    public List<GymDto> getAllGyms() {
        List<Gym> gyms = gymRepository.findAll();
        return gymMapper.entitiesToDtos(gyms);
    }

    public GymDto createGym(GymCreateDto createGymDTO) {
        Gym gym = gymMapper.createGymDTOToGym(createGymDTO);
        Gym savedGym = gymRepository.save(gym);
        return gymMapper.gymToGymDTO(savedGym);
    }

    public GymDto getGymById(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gym not found with id: " + id));
        return gymMapper.gymToGymDTO(gym);
    }

    public void updateGym(Long id, GymUpdateDto updateGymDTO) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gym not found with id: " + id));
        gymMapper.updateGymFromDTO(updateGymDTO, gym);
        gymRepository.save(gym);
    }
}
