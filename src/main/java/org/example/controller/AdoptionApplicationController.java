package org.example.controller;

import org.example.model.AdoptionApplication;
import org.example.model.User;
import org.example.service.AdoptionApplicationService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdoptionApplicationController {

    @Autowired
    private AdoptionApplicationService adoptionApplicationService;

    @Autowired
    private UserService userService;

    @PostMapping("/adopt")
    public ResponseEntity<AdoptionApplication> adoptPet(@RequestBody AdoptionApplication adoptionApplication) {
        // Get the logged-in user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User applicant = userService.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Set the applicant in the adoption application
        adoptionApplication.setApplicant(applicant);

        AdoptionApplication savedApplication = adoptionApplicationService.submitApplication(adoptionApplication);
        return ResponseEntity.ok(savedApplication);
    }
}