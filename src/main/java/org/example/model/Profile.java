package org.example.model;

import java.util.Objects;

public class Profile {
    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public Profile(String email, String firstName, String lastName, String address, String phone) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public Profile() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(email, profile.email)
                && Objects.equals(firstName, profile.firstName)
                && Objects.equals(lastName, profile.lastName)
                && Objects.equals(address, profile.address)
                && Objects.equals(phone, profile.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, address, phone);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
