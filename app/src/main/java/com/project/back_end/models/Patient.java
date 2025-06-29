package com.project.back_end.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Patient extends BasePerson {

    // id inherited from BaseModel
    // password inherited from BasePerson

    // Patient's full name.
    @Column(nullable = false, length = 100)
    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    // Patient's email address.
    @Column(unique = true, nullable = false, length = 100)
    @Email // validates that the email address follows a valid email format (e.g., patient@example.com).
    @NotNull
    @Size(max = 100)
    private String email;

    // Patient's phone number.
    @Pattern(regexp = "^[0-9]{10}$") // validates that the phone number must be exactly 10 digits long.
    @Column(nullable = false, length = 10)
    @NotNull
    private String phone;

    // Patient's address.
    @Column(nullable = false, length = 255)
    @NotNull
    @Size(max = 255)
    private String address;

    // field is_active to deactivate patients without deleting them to keep history records
    @Column(nullable = false)
    @JsonProperty("is_active")
    private boolean isActive;

    public Patient() {
        super();
        isActive = true;
    }

    public Patient(String name, String email, String password, String phone, String address) {
        this();
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Standard getter and setter methods are provided for all fields: id, name, email, password, phone, and address.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
