package org.example.controller;

import org.example.model.Shelter;
import org.example.repository.AdopterRepository;
import org.example.repository.ShelterRepository;
import org.example.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShelterController {

    private ShelterRepository shelterRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ShelterController(ShelterRepository shelterRepository, PasswordEncoder passwordEncoder) {
        this.shelterRepository = shelterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private ShelterService shelterService;

    @GetMapping
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    @GetMapping("/shelters/{id}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable Long id) {
        return shelterService.getShelterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping ("/shelters/register")
    public ResponseEntity<?> registerShelter(@RequestBody Shelter shelter) {
        try {
            shelter.setPassword(passwordEncoder.encode(shelter.getPassword()));
            shelter.setRole("ROLE_SHELTER");
            Shelter savedShelter = shelterRepository.save(shelter);
            return new ResponseEntity<>(savedShelter, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/shelters/{id}")
    public ResponseEntity<Shelter> updateShelter(@PathVariable Long id, @RequestBody Shelter shelterDetails) {
        return shelterService.getShelterById(id)
                .map(shelter -> {
                    shelter.setShelterName(shelterDetails.getShelterName());
                    shelter.setLocation(shelterDetails.getLocation());
                    shelter.setWebSite(shelterDetails.getWebSite());
                    shelter.setOperatingHours(shelterDetails.getOperatingHours());
                    shelter.setDescription(shelterDetails.getDescription());
                    return ResponseEntity.ok(shelterService.updateShelter(id, shelter));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/shelters/id/{id}")
    public ResponseEntity<Void> deleteShelter(@PathVariable Long id) {
        shelterService.deleteShelter(id);
        return ResponseEntity.noContent().build();
    }
}