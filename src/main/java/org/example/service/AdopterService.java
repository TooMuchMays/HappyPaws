package org.example.service;

import org.example.model.Adopter;
import org.example.model.AdoptionApplication;
import org.example.model.User;
import org.example.repository.AdopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.repository.AdoptionApplicationRepository;

import java.util.List;

@Service
public class AdopterService {

    @Autowired
    private AdopterRepository adopterRepository;
    private AdoptionApplicationRepository adoptionApplicationRepository;

    public Adopter registerAdopter(Adopter adopter) {
        if (adopter.getEmail() == null || adopter.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Adopter email cannot be empty");
        }

        if (adopterRepository.existsByEmail(adopter.getEmail())) {
            throw new IllegalArgumentException("Adopter email already exists");
        }
        return adopterRepository.save(adopter);
    }

    public Adopter getAdopterById(Long id) {
        return adopterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adopter not found with id: " + id));
    }

    public Adopter updateAdopter(Long id, Adopter adopter) {
        Adopter existingAdopter = adopterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adopter not found with id: " + id));
        return adopterRepository.save(existingAdopter);
    }

    public List<Adopter> getAllAdopters() {
        return adopterRepository.findAll();
    }

    public void deleteAdopter(Long id) {
        adopterRepository.deleteById(id);
    }

//    public AdoptionApplication submitAdoptionApplication(User applicant, Long petId) {
//        AdoptionApplication application = new AdoptionApplication();
//        application.setApplicant(applicant);
//        application.setId(petId);
//        return adoptionApplicationRepository.save(application);
    }

