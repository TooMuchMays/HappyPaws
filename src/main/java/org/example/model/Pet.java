package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pets")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})

public class Pet {
    @Id
    @SequenceGenerator(
            name = "pets_id_seq",
            sequenceName = "pets_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pets_id_seq")
    private long id;
    private String petName;
    private String species;
    private String breed;
    private int age;
    private String sex;
    private String size;
    private String vaccinationStatus;
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    private String adoptionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id", referencedColumnName = "id", nullable = false)
    private Shelter shelter;

    public Pet() {
    }

    public Pet(long id, String pet_name, String species, String breed,
               int age, String sex, String size, String vaccinationStatus,
               String description, String imageUrl, String adoptionStatus) {
        this.id = id;
        this.petName = pet_name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.sex = sex;
        this.size = size;
        this.vaccinationStatus = vaccinationStatus;
        this.description = description;
        this.imageUrl = imageUrl;
        this.adoptionStatus = adoptionStatus;
//        this.shelterId = shelterId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getVaccinationStatus() {
        return vaccinationStatus;
    }

    public void setVaccinationStatus(String vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
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

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id
                && Float.compare(age, pet.age) == 0
//                && shelterId == pet.shelterId
                && Objects.equals(petName, pet.petName)
                && Objects.equals(species, pet.species)
                && Objects.equals(breed, pet.breed)
                && Objects.equals(sex, pet.sex)
                && Objects.equals(size, pet.size)
                && Objects.equals(vaccinationStatus, pet.vaccinationStatus)
                && Objects.equals(description, pet.description)
                && Objects.equals(imageUrl, pet.imageUrl)
                && Objects.equals(adoptionStatus, pet.adoptionStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petName, species, breed, age, sex, size,
                vaccinationStatus, description, imageUrl, adoptionStatus);
    }

    @Override
    public String toString() {
        return "Pets{" +
                "id=" + id +
                ", petName='" + petName + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", size='" + size + '\'' +
                ", vaccinationStatus='" + vaccinationStatus + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", adoptionStatus='" + adoptionStatus + '\'' +
//                ", shelterId=" + shelterId +
                '}';
    }
}

