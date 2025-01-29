package org.example.service;

import org.example.model.Pet;
import org.example.model.Shelter;
import org.example.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;

    public Shelter registerShelter(Shelter shelter) {
        if (shelter.getShelterName() == null || shelter.getShelterName().isEmpty()) {
            throw new IllegalArgumentException("Shelter name cannot be empty");
        }
        if (shelterRepository.existsByShelterName(shelter.getShelterName())) {
            throw new IllegalArgumentException("Shelter name already exists");
        }
        if (shelter.getLocation() == null || shelter.getLocation().isEmpty()) {
            throw new IllegalArgumentException("Shelter location cannot be empty");
        }
        return shelterRepository.save(shelter);
    }

    public Optional<Shelter> getShelterById(Long id) {
        return shelterRepository.findById(id);
    }

    public Shelter updateShelter(Long id, Shelter shelterDetails) {
        Shelter existingShelter = shelterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Shelter not found with id: " + id));
        existingShelter.setShelterName(shelterDetails.getShelterName());
        existingShelter.setLocation(shelterDetails.getLocation());
        existingShelter.setWebSite(shelterDetails.getWebSite());
        existingShelter.setOperatingHours(shelterDetails.getOperatingHours());
        existingShelter.setDescription(shelterDetails.getDescription());
        return shelterRepository.save(existingShelter);
    }

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    public void deleteShelter(Long id) {
        shelterRepository.deleteById(id);
    }

    public Shelter enlistPet(Long shelterId, Pet pet) {
        Shelter shelter = shelterRepository.findById(shelterId)
                .orElseThrow(() -> new IllegalArgumentException("Shelter not found with id: " + shelterId));
        shelter.getPets().add(pet);
        pet.setShelter(shelter);
        return shelterRepository.save(shelter);
    }
}