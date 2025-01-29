package org.example.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "id")
@DiscriminatorValue("Admin")
public class Admin extends User {

    private String accessLevel;

    public Admin(Long id, String username, String password, String email,
                 String firstName, String lastName, String address, String phone, String accessLevel) {
        super(id, username, password, email, firstName, lastName, address, phone, "ROLE_ADMIN");
        this.accessLevel = accessLevel;
    }

    public Admin(String accessLevel) {
        super();
        this.setRole("ROLE_ADMIN");
        this.accessLevel = accessLevel;
    }

    public Admin() {
        super();
        this.setRole("ROLE_ADMIN");
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        return Objects.equals(accessLevel, admin.accessLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accessLevel);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "accessLevel='" + accessLevel + '\'' +
                ", " + super.toString() +
                '}';
    }
}