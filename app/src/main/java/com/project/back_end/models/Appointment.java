package com.project.back_end.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Appointment extends BaseModel{

    // id inherited from BaseModel

    /** Doctor assigned to this appointment. */
    @ManyToOne
    @NotNull
    private Doctor doctor;

    /** Patient assigned to this appointment. */
    @ManyToOne
    @NotNull
    private Patient patient;

    /** Date and time when the appointment is scheduled to occur. */
    @NotNull
    @Future   // ensures that the appointment time is always in the future when the appointment is created.
    private LocalDateTime appointmentTime;

    /**
     * Current status of the appointment. <br>
     *   0 = scheduled <br>
     *   1 = completed
     */
    @NotNull
    @Min(0) @Max(1)
    @Column(nullable = false)
    private Integer status;

    // Constructor(s):

    /** A no-argument constructor. */
    public Appointment() {
        super();
        status = 0;
    }

    /** A parameterized constructor can be added as needed to initialize fields. */
    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime) {
        this();
        this.doctor = doctor;
        this.patient = patient;

        if (appointmentTime == null || appointmentTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment time must be in the future");
        }
        this.appointmentTime = appointmentTime;
    }

    /**
     * This method is used to get an estimated appointment end time for display purposes.
     * Calculated by adding one hour to the appointmentTime field. <br>
     * <i>note: this method is a transient field and it is not persisted in the database</i>
     * @return LocalDateTime representing the end time of the appointment
     */
    @Transient
    public LocalDateTime getEndTime() {
        return appointmentTime.plusHours(1);
    }

    /**
     * Extracts only the date part from the appointmentTime field.<br>
     * <i>note: this method is a transient field and it is not persisted in the database</i>
     * @return LocalDate representing the date of the scheduled appointment
     */
    @Transient
    public LocalDate getAppointmentDate() {
        return appointmentTime.toLocalDate();
    }

    /**
     * Extracts only the time part from the appointmentTime field.<br>
     * <i>note: this method is a transient field and it is not persisted in the database</i>
     * @return LocalTime representing the time of the scheduled appointment
     */
    @Transient
    public LocalTime getAppointmentTimeOnly() {
        return appointmentTime.toLocalTime();
    }

    // Standard getter and setter methods are provided for accessing and modifying the fields: doctor, patient, appointmentTime, status, etc.
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

