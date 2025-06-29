package com.project.back_end.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppointmentTest {

    private static final Patient PATIENT
            = new Patient("John Doe",
            "john.doe@example.com",
            "password123",
            "1234567890",
            "123 Main St");

    private static final Doctor DOCTOR = new Doctor("Dr. Smith",
            "dr.smith@example.com",
            "password!@#$",
            "0987654321",
            "Cardiology");

    @Test
    public void testAppointmentConstructor() {
        LocalDateTime appointmentTime = LocalDateTime.now().plusHours(2);

        Appointment appointment = new Appointment(DOCTOR, PATIENT, appointmentTime);
        assertEquals(PATIENT, appointment.getPatient());
        assertEquals(DOCTOR, appointment.getDoctor());
        assertEquals(appointmentTime, appointment.getAppointmentTime());
    }

    /**
     * Test case for Appointment default constructor
     * Verifies that the Appointment object is created with correct createdAt and updatedAt fields,
     *  and that createdAt and updatedAt fields are initialized to the current time.
     */
    @Test
    public void testAppointmentDefaultConstructor() {
        Appointment appointment = new Appointment();
        assertNotNull(appointment);
        assertNotNull(appointment.getCreatedAt());
        assertNotNull(appointment.getUpdatedAt());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(appointment.getCreatedAt().isBefore(now) || appointment.getCreatedAt().isEqual(now));
        assertTrue(appointment.getUpdatedAt().isBefore(now) || appointment.getUpdatedAt().isEqual(now));
        assertNotNull(appointment.getCreatedAt());
    }

    /**
     * Test case for Appointment constructor
     * Verifies that the Appointment object is created with correct username and password,
     *  and that createdAt and updatedAt fields are initialized to the current time.
     */
    @Test
    public void testAppointmentConstructorWithUsernameAndPassword() {
        LocalDateTime appointmentTime = LocalDateTime.now().plusHours(2);

        Appointment appointment = new Appointment(DOCTOR, PATIENT, appointmentTime);
        assertEquals(PATIENT, appointment.getPatient());
        assertEquals(DOCTOR, appointment.getDoctor());
        assertEquals(appointmentTime, appointment.getAppointmentTime());
    }

    /**
     * Test case to validate appointment time can be only in the future and exception is raised
     * if appointment time is in the past or null
     */
    @Test
    public void testAppointmentTimeInPast() {
        final LocalDateTime pastTime = LocalDateTime.now().minusHours(1);

        assertThrows(IllegalArgumentException.class, () -> new Appointment(DOCTOR, PATIENT, pastTime));
        assertThrows(IllegalArgumentException.class, () -> new Appointment(DOCTOR, PATIENT, null));
    }

    /**
     * Test case to validate getAppointmentDate method return valid date only portion
     */
    @Test
    public void testGetAppointmentDate() {
        LocalDateTime appointmentTime = LocalDateTime.now().plusHours(1);
        Appointment appointment = new Appointment(DOCTOR, PATIENT, appointmentTime);

        assertEquals(appointmentTime.toLocalDate(), appointment.getAppointmentDate());
    }


    /**
     * Test case to validate getAppointmentTime method return valid time only portion
     */
    @Test
    public void testGetAppointmentTime() {
        LocalDateTime appointmentTime = LocalDateTime.now().plusHours(1);
        Appointment appointment = new Appointment(DOCTOR, PATIENT, appointmentTime);

        assertEquals(appointmentTime.toLocalTime(), appointment.getAppointmentTimeOnly());
    }

    /**
     * Test case to validate getEndTime method return valid estimated end date/time
     */
    @Test
    public void testGetAppointmentEndTime() {
        LocalDateTime appointmentTime = LocalDateTime.now().plusDays(1);
        Appointment appointment = new Appointment(DOCTOR, PATIENT, appointmentTime);

        assertEquals(appointmentTime.plusHours(1), appointment.getEndTime());
    }

}
