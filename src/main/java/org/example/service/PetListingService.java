package org.example.service;

import org.example.dto.PetDto;
import org.example.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetListingService {
    List<PetDto> getAllPets();
    Optional<Pet> getPetById(Long petId);
    Pet savePet(Pet pet);
    Optional<Pet> updatePetById(Long petId, Pet updatedPet);
    boolean deletePetById(Long petId);
    List<Pet> findByAdoptionStatus(String status);
}