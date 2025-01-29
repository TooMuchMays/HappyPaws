package org.example.service;

import org.example.model.AdoptionApplication;
import org.example.repository.AdoptionApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptionApplicationService {

    @Autowired
    private AdoptionApplicationRepository repository;

    public AdoptionApplication submitApplication(AdoptionApplication application) {
        // Remove the validation for null applicant
        return repository.save(application);
    }
}