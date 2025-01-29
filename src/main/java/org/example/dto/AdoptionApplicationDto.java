package org.example.dto;

import org.example.model.User;

public class AdoptionApplicationDto {

    private User applicantName;
    private String email;
    private Long petId; // Assuming you have a Pet entity

    public User getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(User applicantName) {
        this.applicantName = applicantName;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
