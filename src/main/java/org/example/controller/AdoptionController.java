package org.example.controller;

import org.example.model.Adoption;
import org.example.repository.AdoptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adoptions")
public class AdoptionController {

    @Autowired
    private AdoptionRepository adoptionRepository;

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public Adoption createAdoption(@RequestParam Long petId,
                                   @RequestParam String petName,
                                   @RequestParam String adopterName,
                                   @RequestParam String adopterEmail,
                                   @RequestParam String adopterPhone) {
        Adoption adoption = new Adoption();
        adoption.setPetId(petId);
        adoption.setPetName(petName);
        adoption.setAdopterName(adopterName);
        adoption.setAdopterEmail(adopterEmail);
        adoption.setAdopterPhone(adopterPhone);
        return adoptionRepository.save(adoption);
    }
}