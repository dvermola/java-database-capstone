package project.back_end.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prescriptions")
public class Prescription {
    
    @Id
    private String id;
    
    @NotBlank(message = "Patient name is required")
    @Size(min = 3, max = 100, message = "Patient name must be between 3 and 100 characters")
    private String patientName;
    
    @NotNull(message = "Appointment ID is required")
    @JsonProperty("appointment_id")
    private Long appointmentId;
    
    @NotBlank(message = "Medication name is required")
    @Size(min = 3, max = 100, message = "Medication name must be between 3 and 100 characters")
    private String medication;
    
    @NotBlank(message = "Dosage is required")
    @Size(min = 3, max = 20, message = "Dosage must be between 3 and 20 characters")
    private String dosage;
    
    @Size(max = 200, message = "Doctor notes must not exceed 200 characters")
    @JsonProperty("doctor_notes")
    private String doctorNotes;
    
    @JsonProperty("refill_count")
    private Integer refillCount;
    
    @Size(max = 30, message = "Pharmacy name must not exceed 30 characters")
    @JsonProperty("pharmacy_name")
    private String pharmacyName;
    
    
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getPatientName() {
        return patientName;
    }
    
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    
    public Long getAppointmentId() {
        return appointmentId;
    }
    
    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
    
    public String getMedication() {
        return medication;
    }
    
    public void setMedication(String medication) {
        this.medication = medication;
    }
    
    public String getDosage() {
        return dosage;
    }
    
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
    
    public String getDoctorNotes() {
        return doctorNotes;
    }
    
    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }
    
    public Integer getRefillCount() {
        return refillCount;
    }
    
    public void setRefillCount(Integer refillCount) {
        this.refillCount = refillCount;
    }
    
    public String getPharmacyName() {
        return pharmacyName;
    }
    
    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }
}