package project.back_end.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "appointment")
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "doctor is required")
    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @NotBlank(message = "patient is required")
    @ManyToOne(optional = false)
    private Patient patient;

    @NotBlank(message = "appointment time is required")
    @Column(name = "appointment_time", nullable = false)
    @Future(message = "Appointment time must be in the future")
    @JsonProperty("appointment_time")
    private LocalDateTime appointmentTime;

    @NotBlank(message = "status is required")
    @Column(nullable = false)
    private int status;
    
    @Column(length = 255)
    @JsonProperty("reason_for_visit")
    private String reasonForVisit;
    
    @Column(columnDefinition = "TEXT")
    private String notes;

    
    // Helper methods
    @Transient
    public LocalDateTime getEndTime() {
        return appointmentTime.plusHours(1);
    }
    
    @Transient
    public LocalDate getAppointmentDate() {
        return appointmentTime.toLocalDate();
    }
    
    @Transient
    public LocalTime getAppointmentTimeOnly() {
        return appointmentTime.toLocalTime();
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }
    
    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getReasonForVisit() {
        return reasonForVisit;
    }
    
    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
}