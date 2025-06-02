package project.back_end.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "patient")
public class Patient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;
  
  @NotBlank(message = "Name is required")
  @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
  private String name;
  
  @NotBlank(message = "Email is required")
  @Email(message = "Email must be valid")
  private String email;
  
  @NotBlank(message = "Password is required")
  @Size(min = 8, max = 50, message = "Specialty must be between 8 and 50 characters")
  @JsonIgnore
  private String password;
  
  @NotBlank(message = "Phone number is required")
  @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
  private String phone;
  
  @NotBlank(message = "Address is required")
  @Size(max = 255, message = "Address must not exceed 255 characters")
  private String address;
  
  @JsonProperty("birth_date")
  private java.time.LocalDate dateOfBirth;
  
  @Size(max = 50, message = "Emergency contact must not exceed 50 characters")
  @JsonProperty("emergency_contact")
  private String emergencyContact;
  
  @Pattern(regexp = "\\d{10}", message = "Emergency contact Phone number must be 10 digits")
  @JsonProperty("emergency_phone")
  private String emergencyContactPhone;
  
  @Size(max = 50, message = "Insurance provider must not exceed 50 characters")
  @JsonProperty("insurance_provider")
  private String insuranceProvider;

  // Getters and Setters
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
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
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
  
  public java.time.LocalDate getDateOfBirth() {
    return dateOfBirth;
  }
  
  public void setDateOfBirth(java.time.LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
  
  public String getEmergencyContact() {
    return emergencyContact;
  }
  
  public void setEmergencyContact(String emergencyContact) {
    this.emergencyContact = emergencyContact;
  }

  public String getEmergencyContactPhone() {
    return emergencyContactPhone;
  }

  public void setEmergencyContactPhone(String emergencyContactPhone) {
    this.emergencyContactPhone = emergencyContactPhone;
  }

  public String getInsuranceProvider() {
    return insuranceProvider;
  }
  
  public void setInsuranceProvider(String insuranceProvider) {
    this.insuranceProvider = insuranceProvider;
  }
}