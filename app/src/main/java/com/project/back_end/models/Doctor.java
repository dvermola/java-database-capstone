package com.project.back_end.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Doctor extends BasePerson {

    // id inherited from BaseModel
    // password inherited from BasePerson

    // Doctor's email address.
    @Column(unique = true, nullable = false, length = 100)
    @Email
    @NotNull
    @Size(max = 100)
    private String email;

    // the doctor's name.
    @Column(nullable = false, length = 100)
    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @Column(nullable = false)
    @NotNull
    private String specialty;

    // Doctor's phone number.
    @Pattern(regexp = "^[0-9]{10}$") // validates that the phone number must be exactly 10 digits long.
    @Column(nullable = false, length = 10)
    @NotNull
    private String phone;

    // Available times for the doctor in a list of time slots.
    //  - Each time slot is represented as a string (e.g., "09:00-10:00", "10:00-11:00").
    //  - The @ElementCollection annotation ensures that the list of time slots is stored as a separate collection in the database.
    @Column(nullable = false)
    @NotNull
    @ElementCollection
    private Set<String> availableTimes;

    // field is_active to deactivate patients without deleting them to keep history records
    @Column(nullable = false)
    @JsonProperty("is_active")
    private boolean isActive;

    public Doctor() {
        super();
        isActive = true;
    }

    public Doctor(String name, String email, String password, String phone, String specialty) {
        this();
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.specialty = specialty;
    }

    // Getters and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<String> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(Set<String> availableTimes) {
        this.availableTimes = availableTimes;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

