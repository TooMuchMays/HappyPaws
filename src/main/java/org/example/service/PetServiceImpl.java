package org.example.service;

import org.example.dto.PetDto;
import org.example.model.Pet;
import org.example.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<PetDto> getAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream()
                .map(PetDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pet> getPetById(Long petId) {
        return petRepository.findById(petId);
    }

    @Override
    public Pet savePet(Pet pet) {
        pet.setImageUrl("images/" + pet.getImageUrl());
        return petRepository.save(pet);
    }

    @Override
    public Optional<Pet> updatePetById(Long petId, Pet updatedPet) {
        return petRepository.findById(petId).map(existingPet -> {
            existingPet.setPetName(updatedPet.getPetName());
            existingPet.setSpecies(updatedPet.getSpecies());
            existingPet.setBreed(updatedPet.getBreed());
            existingPet.setAge(updatedPet.getAge());
            existingPet.setSex(updatedPet.getSex());
            existingPet.setSize(updatedPet.getSize());
            existingPet.setVaccinationStatus(updatedPet.getVaccinationStatus());
            existingPet.setDescription(updatedPet.getDescription());
            existingPet.setImageUrl("images/" + updatedPet.getImageUrl());
            existingPet.setAdoptionStatus(updatedPet.getAdoptionStatus());
            return petRepository.save(existingPet);
        });
    }

    @Override
    public boolean deletePetById(Long petId) {
        if (petRepository.existsById(petId)) {
            petRepository.deleteById(petId);
            return true;
        }
        return false;
    }

    @Override
    public List<Pet> findByAdoptionStatus(String status) {
        return petRepository.findByAdoptionStatus(status);
    }
}