package com.example.ServisZakazivanje.controller;

import com.example.ServisZakazivanje.domain.Gym;
import com.example.ServisZakazivanje.dto.GymCreateDto;
import com.example.ServisZakazivanje.dto.GymDto;
import com.example.ServisZakazivanje.mapper.GymMapper;
import com.example.ServisZakazivanje.security.CheckSecurity;
import com.example.ServisZakazivanje.service.GymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/gyms")
public class GymController {

    private final GymService gymService;
    private final GymMapper gymMapper;

    @Autowired
    public GymController(GymService gymService, GymMapper gymMapper) {
        this.gymService = gymService;
        this.gymMapper = gymMapper;
    }

    @GetMapping
    public ResponseEntity<List<GymDto>> getAllGyms() {
//        List<Gym> gyms = gymService.getAllGyms();
//        List<GymDto> gymDTOs = gymMapper.entitiesToDtos(gyms);
        return new ResponseEntity<>(gymService.getAllGyms(), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"admin", "menager"})
    public ResponseEntity<GymDto> createGym(@RequestHeader("Authorization") String authorization,
                                            @RequestBody GymCreateDto createGymDTO) {
        GymDto gymDTO = gymService.createGym(createGymDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(gymDTO.getId()).toUri();
        return ResponseEntity.created(location).body(gymDTO);
    }

    // Add more methods for other operations...

}
