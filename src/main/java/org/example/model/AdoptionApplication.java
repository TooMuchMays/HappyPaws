package org.example.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "adoption_applications")
public class AdoptionApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private User applicant;

    private String status;
    private LocalDateTime timestamp;
    private LocalDateTime applicationDate;
    private LocalDateTime approvalDate;
    private String comments;

    public AdoptionApplication(Pet pet, User applicant, String status, LocalDateTime timestamp,
                               LocalDateTime applicationDate, LocalDateTime approvalDate, String comments) {
        this.pet = pet;
        this.applicant = applicant;
        this.status = status;
        this.timestamp = timestamp;
        this.applicationDate = applicationDate;
        this.approvalDate = approvalDate;
        this.comments = comments;
    }

    public AdoptionApplication() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDateTime applicationDate) {
        this.applicationDate = applicationDate;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionApplication that = (AdoptionApplication) o;
        return Objects.equals(id, that.id)
                && Objects.equals(pet, that.pet)
                && Objects.equals(applicant, that.applicant)
                && Objects.equals(status, that.status)
                && Objects.equals(timestamp, that.timestamp)
                && Objects.equals(applicationDate, that.applicationDate)
                && Objects.equals(approvalDate, that.approvalDate)
                && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pet, applicant, status, timestamp, applicationDate, approvalDate, comments);
    }

    @Override
    public String toString() {
        return "AdoptionApplication{" +
                "id=" + id +
                ", pet=" + pet +
                ", applicant=" + applicant +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                ", applicationDate=" + applicationDate +
                ", approvalDate=" + approvalDate +
                ", comments='" + comments + '\'' +
                '}';
    }
}