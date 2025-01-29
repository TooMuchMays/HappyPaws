package org.example.controller;


import org.example.model.Adopter;
import org.example.repository.AdopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@RestController
public class AdopterController {

    private AdopterRepository adopterRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdopterController(AdopterRepository adopterRepository, PasswordEncoder passwordEncoder){
        this.adopterRepository = adopterRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/adopters")
    public List<Adopter> getAdopterList(){
        return adopterRepository.findAll();
    }

    @GetMapping("/adopters/{id}")
    public Adopter getAdopterById(@PathVariable long id){
        return adopterRepository.getReferenceById(id);
    }

    @PostMapping("/adopters")
    public void addAdopter(@RequestBody Adopter adopter) {
        adopterRepository.save(adopter);
    }

    @PutMapping("/adopters/id/{id}")
    public void updateAdopterById(@PathVariable long id, @RequestBody Adopter updatedAdopter) {
        Adopter existingAdopter = adopterRepository.findById(id).orElse(null);

        if (existingAdopter != null) {
            existingAdopter.setUsername(updatedAdopter.getUsername());
            existingAdopter.setPassword(updatedAdopter.getPassword());
            existingAdopter.setEmail(updatedAdopter.getEmail());
            existingAdopter.setFirstName(updatedAdopter.getFirstName());
            existingAdopter.setLastName(updatedAdopter.getLastName());
            existingAdopter.setAddress(updatedAdopter.getAddress());
            existingAdopter.setPhone(updatedAdopter.getPhone());
            existingAdopter.setHousingType(updatedAdopter.getHousingType());
            adopterRepository.save(existingAdopter);
        }
    }

    @DeleteMapping("/adopters/id/{id}")
    public void deleteAdopterById(@PathVariable long id){
        adopterRepository.deleteById(id);
    }
    @PostMapping("/adopters/register")
    public ResponseEntity<?> registerAdopter(@RequestBody Adopter adopter) {
        try {
            adopter.setPassword(passwordEncoder.encode(adopter.getPassword()));
            adopter.setRole("ROLE_ADOPTER");
            Adopter savedAdopter = adopterRepository.save(adopter);
            return new ResponseEntity<>(savedAdopter, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
