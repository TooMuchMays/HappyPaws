package org.example.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pet_listings")
public class PetListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String species;
    private String breed;
    private int age;
    private String description;
    private String photos;
    private String status;

    public PetListing(String name, Long id, String species, String breed, int age,
                      String photos, String status, String description) {
        this.name = name;
        this.id = id;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.photos = photos;
        this.status = status;
        this.description = description;
    }

    public PetListing() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetListing that = (PetListing) o;
        return age == that.age
                && Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(species, that.species)
                && Objects.equals(breed, that.breed)
                && Objects.equals(description, that.description)
                && Objects.equals(photos, that.photos)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, species, breed, age, description, photos, status);
    }

    @Override
    public String toString() {
        return "PetListing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", photos='" + photos + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
