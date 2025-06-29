package com.project.back_end.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

/**
 * The collection name is specified as "prescriptions" to map this class to
 * the "prescriptions" collection in MongoDB.
 */
@Document(collection = "prescriptions")
public class Prescription {

    // When working with NoSQL databases (e.g., MongoDB), use org.springframework.data.annotation.Id.
    @Id
    private String id;

    // Name of the patient receiving the prescription.
    @NotNull
    @Size(min = 3, max = 100)
    @Field(targetType = FieldType.STRING, write = Field.Write.NON_NULL)
    private String patientName;

    // ID of the associated appointment where the prescription was given.
    @NotNull
    @Field(targetType = FieldType.INT64, write = Field.Write.NON_NULL)
    private Long appointmentId;

    // Medication prescribed to the patient.
    @NotNull
    @Size(min = 3, max = 100)
    @Field(targetType = FieldType.STRING, write = Field.Write.NON_NULL)
    private String medication;

    // Dosage information for the prescribed medication.
    @NotNull
    @Field(targetType = FieldType.STRING, write = Field.Write.NON_NULL)
    private String dosage;

    // Any additional notes or instructions from the doctor regarding the prescription.
    @Size(max = 200)
    @Field(targetType = FieldType.STRING)
    private String doctorNotes;

    @Field(targetType = FieldType.DATE_TIME, write = Field.Write.NON_NULL)
    private final LocalDateTime createdAt;

    @Field(targetType = FieldType.DATE_TIME, write = Field.Write.NON_NULL)
    private LocalDateTime updatedAt;


    // Constructors:
    public Prescription() {
        super();
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    public Prescription(String patientName, String medication, String dosage, String doctorNotes, Long appointmentId) {
        this();
        this.patientName = patientName;
        this.appointmentId = appointmentId;
        this.medication = medication;
        this.dosage = dosage;
        this.doctorNotes = doctorNotes;
    }

    // Getters and Setters:
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}