package org.example.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shelters")
@PrimaryKeyJoinColumn(name = "id")
public class Shelter extends User {

    private String shelterName;
    private String location;
    private String webSite;
    private String operatingHours;
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shelter", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pet> pets;

    public Shelter(Long id, String username, String password, String email, String shelterName, String location, String webSite, String operatingHours, String description) {
        super(id, username, password, email);
        this.shelterName = shelterName;
        this.location = location;
        this.webSite = webSite;
        this.operatingHours = operatingHours;
        this.description = description;
    }

    public Shelter() {

    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}