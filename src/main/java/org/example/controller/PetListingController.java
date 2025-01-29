package org.example.controller;

import org.example.dto.PetDto;
import org.example.service.PetListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetListingController {

    @Autowired
    private PetListingService petListingService;

    @GetMapping("/all")
    public List<PetDto> getAllPets() {
        return petListingService.getAllPets();
    }
}