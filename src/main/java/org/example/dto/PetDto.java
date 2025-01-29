package org.example.dto;

import java.util.Objects;


import jakarta.persistence.JoinColumn;
import org.example.model.Pet;
import org.example.model.Shelter;

public class PetDto {
        private Long id;
        private String adoptionStatus;
        private int age;
        private String breed;
        private String description;
        private String imageUrl;
        private String petName;
        private String sex;
        @JoinColumn(name = "shelter_id", referencedColumnName = "id", nullable = false)
        private Shelter shelter;
        private String size;
        private String species;
        private String vaccinationStatus;

        // Constructor that accepts a Pet object
        public PetDto(Pet pet) {
            this.id = pet.getId();
            this.adoptionStatus = pet.getAdoptionStatus();
            this.age = pet.getAge();
            this.breed = pet.getBreed();
            this.description = pet.getDescription();
            this.imageUrl = pet.getImageUrl();
            this.petName = pet.getPetName();
            this.sex = pet.getSex();
            this.size = pet.getSize();
            this.species = pet.getSpecies();
            this.vaccinationStatus = pet.getVaccinationStatus();
        }

    public PetDto(long id, String petName, String breed, String shelterName) {
    }

    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getAdoptionStatus() {
            return adoptionStatus;
        }

        public void setAdoptionStatus(String adoptionStatus) {
            this.adoptionStatus = adoptionStatus;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getBreed() {
            return breed;
        }

        public void setBreed(String breed) {
            this.breed = breed;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPetName() {
            return petName;
        }

        public void setPetName(String petName) {
            this.petName = petName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

//        public Long getShelterId() {
//            return shelterId;
//        }

//        public void setShelterId(Long shelterId) {
//            this.shelterId = shelterId;
//        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getSpecies() {
            return species;
        }

        public void setSpecies(String species) {
            this.species = species;
        }

        public String getVaccinationStatus() {
            return vaccinationStatus;
        }

        public void setVaccinationStatus(String vaccinationStatus) {
            this.vaccinationStatus = vaccinationStatus;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetDto petDto)) return false;
        return age == petDto.age && Objects.equals(id, petDto.id) && Objects.equals(adoptionStatus, petDto.adoptionStatus) && Objects.equals(breed, petDto.breed) && Objects.equals(description, petDto.description) && Objects.equals(imageUrl, petDto.imageUrl) && Objects.equals(petName, petDto.petName) && Objects.equals(sex, petDto.sex) && Objects.equals(shelter, petDto.shelter) && Objects.equals(size, petDto.size) && Objects.equals(species, petDto.species) && Objects.equals(vaccinationStatus, petDto.vaccinationStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adoptionStatus, age, breed, description, imageUrl, petName, sex, shelter, size, species, vaccinationStatus);
    }

    @Override
    public String toString() {
        return "PetDto{" +
                "id=" + id +
                ", adoptionStatus='" + adoptionStatus + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", petName='" + petName + '\'' +
                ", sex='" + sex + '\'' +
                ", shelter=" + shelter +
                ", size='" + size + '\'' +
                ", species='" + species + '\'' +
                ", vaccinationStatus='" + vaccinationStatus + '\'' +
                '}';
    }
}