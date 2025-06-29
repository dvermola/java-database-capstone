package com.project.back_end.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionTest {

    private static final String PATIENT_NAME = "John Doe";
    private static final String MEDICATION = "Medication A";
    private static final String DOSAGE = "Take 1 tablet daily";
    private static final String DOCTOR_NOTES = "Take with food";
    private static final Long APPOINTMENT_ID = 12345L;

    /**
     * Test the Prescription default constructor
     */
    @Test
    public void testPrescriptionDefaultConstructor() {
        Prescription prescription = new Prescription();
        assertNotNull(prescription.getCreatedAt());
        assertNotNull(prescription.getUpdatedAt());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(prescription.getCreatedAt().isBefore(now) || prescription.getCreatedAt().isEqual(now));
        assertTrue(prescription.getUpdatedAt().isBefore(now) || prescription.getUpdatedAt().isEqual(now));
        assertEquals(prescription.getCreatedAt(), prescription.getUpdatedAt());
    }

    /**
     * Tests the Prescription constructor with empty strings for user's name and password.
     * This verifies that the constructor allows empty strings without throwing exceptions,
     * as there are no explicit checks for empty strings in the implementation.
     */
    @Test
    public void testPatientConstructorWithEmptyStrings() {
//        Prescription prescription = new Prescription(PATIENT_NAME, MEDICATION, DOSAGE, DOCTOR_NOTES, APPOINTMENT_ID);
        Prescription prescription = new Prescription("", "", "", "", APPOINTMENT_ID);
        assertEquals("", prescription.getPatientName());
        assertEquals("", prescription.getMedication());
        assertEquals("", prescription.getDosage());
        assertEquals("", prescription.getDoctorNotes());
        assertEquals(APPOINTMENT_ID, prescription.getAppointmentId());
        assertNotNull(prescription.getCreatedAt());
        assertNotNull(prescription.getUpdatedAt());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(prescription.getCreatedAt().isBefore(now) || prescription.getCreatedAt().isEqual(now));
        assertTrue(prescription.getUpdatedAt().isBefore(now) || prescription.getUpdatedAt().isEqual(now));
        assertEquals(prescription.getCreatedAt(), prescription.getUpdatedAt());
    }

    /**
     * Test case for Prescription constructor
     * Verifies that the Admin object is created with correct username and password,
     * and that createdAt and updatedAt fields are initialized to the current time.
     */
    @Test
    public void testPatientConstructorNonEmptyStrings() {
        Prescription prescription = new Prescription(PATIENT_NAME, MEDICATION, DOSAGE, DOCTOR_NOTES, APPOINTMENT_ID);
        assertEquals(PATIENT_NAME, prescription.getPatientName());
        assertEquals(MEDICATION, prescription.getMedication());
        assertEquals(DOSAGE, prescription.getDosage());
        assertEquals(DOCTOR_NOTES, prescription.getDoctorNotes());
        assertEquals(APPOINTMENT_ID, prescription.getAppointmentId());
        assertNotNull(prescription.getCreatedAt());
        assertNotNull(prescription.getUpdatedAt());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(prescription.getCreatedAt().isBefore(now) || prescription.getCreatedAt().isEqual(now));
        assertTrue(prescription.getUpdatedAt().isBefore(now) || prescription.getUpdatedAt().isEqual(now));
        assertEquals(prescription.getCreatedAt(), prescription.getUpdatedAt());
    }

}
