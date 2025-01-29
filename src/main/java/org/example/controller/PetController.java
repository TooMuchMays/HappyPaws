package org.example.controller;

import org.example.dto.PetDto;
import org.example.model.Pet;
import org.example.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(@Qualifier("petServiceImpl") PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public List<PetDto> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long petId) {
        return petService.getPetById(petId)
                .map(pet -> ResponseEntity.ok(pet))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        Pet savedPet = petService.savePet(pet);
        return ResponseEntity.ok(savedPet);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Pet> updatePetById(@PathVariable Long petId, @RequestBody Pet updatedPet) {
        return petService.updatePetById(petId, updatedPet)
                .map(pet -> ResponseEntity.ok(pet))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePetById(@PathVariable Long petId) {
        if (petService.deletePetById(petId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}