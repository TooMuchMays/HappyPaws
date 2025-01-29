package org.example.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "adopters")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("Adopter")
public class Adopter extends User {

    private String housingType;

    public Adopter(Long id, String username, String password, String email,
                   String firstName, String lastName, String address, String phone, String housingType) {
        super(id, username, password, email, firstName, lastName, address, phone, "ROLE_ADOPTER");
        this.housingType = housingType;
    }

    public Adopter(String housingType) {
        super();
        this.setRole("ROLE_ADOPTER");
        this.housingType = housingType;
    }

    public Adopter() {
        super();
        this.setRole("ROLE_ADOPTER");
    }

    public String getHousingType() {
        return housingType;
    }

    public void setHousingType(String housingType) {
        this.housingType = housingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Adopter adopter = (Adopter) o;
        return Objects.equals(housingType, adopter.housingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), housingType);
    }

    @Override
    public String toString() {
        return "Adopter{" +
                "housingType='" + housingType + '\'' +
                ", " + super.toString() +
                '}';
    }
}