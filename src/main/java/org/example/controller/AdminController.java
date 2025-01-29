package org.example.controller;

import org.example.model.Admin;
import org.example.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    private AdminRepository adminRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/admins/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        try {
            // Encode password before saving
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            admin.setRole("ROLE_ADMIN");
            Admin savedAdmin = adminRepository.save(admin);
            return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/admins")
    public List<Admin> getAdminsList(){
        return adminRepository.findAll();
    }

    @GetMapping("/admins/{id}")
    public Admin getAdminById(@PathVariable long id){
        return adminRepository.getReferenceById(id);
    }

    @PostMapping("/admins")
    public void addAdmin(@RequestBody Admin admin) {
        adminRepository.save(admin);
    }

    @PutMapping("/admins/id/{id}")
    public void updateAdminById(@PathVariable long id, @RequestBody Admin updatedAdmin) {
        Admin existingAdmin = adminRepository.findById(id).orElse(null);

        if (existingAdmin != null) {
            existingAdmin.setUsername(updatedAdmin.getUsername());
            existingAdmin.setPassword(updatedAdmin.getPassword());
            existingAdmin.setEmail(updatedAdmin.getEmail());
            existingAdmin.setFirstName(updatedAdmin.getFirstName());
            existingAdmin.setLastName(updatedAdmin.getLastName());
            existingAdmin.setAddress(updatedAdmin.getAddress());
            existingAdmin.setPhone(updatedAdmin.getPhone());
            adminRepository.save(existingAdmin);
        }
    }

    @DeleteMapping("/admins/id/{id}")
    public void deleteAdminById(@PathVariable long id){
        adminRepository.deleteById(id);
    }
}
