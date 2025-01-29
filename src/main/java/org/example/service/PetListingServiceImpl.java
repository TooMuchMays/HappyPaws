package org.example.service;

import org.example.dto.PetDto;
import org.example.model.Pet;
import org.example.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class PetListingServiceImpl implements PetListingService {

    private final PetRepository petRepository;

    @Autowired
    public PetListingServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetDto> getAllPets() {
        return petRepository.findAll().stream()
                .map(pet -> new PetDto(pet.getId(), pet.getPetName(), pet.getBreed(), pet.getShelter().getShelterName()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pet> getPetById(Long petId) {
        return petRepository.findById(petId);
    }

    @Override
    @Transactional
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    @Transactional
    public Optional<Pet> updatePetById(Long petId, Pet updatedPet) {
        return petRepository.findById(petId).map(pet -> {
            pet.setPetName(updatedPet.getPetName());
            pet.setBreed(updatedPet.getBreed());
            pet.setAge(updatedPet.getAge());
            pet.setDescription(updatedPet.getDescription());
            pet.setImageUrl(updatedPet.getImageUrl());
            pet.setSex(updatedPet.getSex());
            pet.setSize(updatedPet.getSize());
            pet.setSpecies(updatedPet.getSpecies());
            pet.setVaccinationStatus(updatedPet.getVaccinationStatus());
            pet.setAdoptionStatus(updatedPet.getAdoptionStatus());
            pet.setShelter(updatedPet.getShelter());
            return petRepository.save(pet);
        });
    }

    @Override
    @Transactional
    public boolean deletePetById(Long petId) {
        return petRepository.findById(petId).map(pet -> {
            petRepository.delete(pet);
            return true;
        }).orElse(false);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pet> findByAdoptionStatus(String status) {
        return petRepository.findByAdoptionStatus(status);
    }
}