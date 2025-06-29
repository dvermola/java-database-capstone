package com.project.back_end.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {

    private static final String NAME = "testuser";
    private static final String PASSWORD = "testpass";
    private static final String EMAIL = "test@example.com";
    private static final String PHONE = "1234567890";
    private static final String SPECIALITY = "General Medicine";

    /**
     * Test the Doctor default constructor
     */
    @Test
    public void testDoctorDefaultConstructor() {
        Doctor user = new Doctor();
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
        assertTrue(user.isActive());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(user.getCreatedAt().isBefore(now) || user.getCreatedAt().isEqual(now));
        assertTrue(user.getUpdatedAt().isBefore(now) || user.getUpdatedAt().isEqual(now));
        assertEquals(user.getCreatedAt(), user.getUpdatedAt());
    }

    /**
     * Tests the Doctor constructor with empty strings for user's name and password.
     * This verifies that the constructor allows empty strings without throwing exceptions,
     * as there are no explicit checks for empty strings in the implementation.
     */
    @Test
    public void testDoctorConstructorWithEmptyStrings() {
        Doctor user = new Doctor("", "", "", "", "");
        assertEquals("", user.getName());
        assertEquals("", user.getEmail());
        assertEquals("", user.getPassword());
        assertEquals("", user.getPhone());
        assertEquals("", user.getSpecialty());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
        assertTrue(user.isActive());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(user.getCreatedAt().isBefore(now) || user.getCreatedAt().isEqual(now));
        assertTrue(user.getUpdatedAt().isBefore(now) || user.getUpdatedAt().isEqual(now));
        assertEquals(user.getCreatedAt(), user.getUpdatedAt());
    }

    /**
     * Tests the Doctor constructor with non-empty strings for user's name and password.
     * This verifies that the constructor allows empty strings without throwing exceptions,
     * as there are no explicit checks for empty strings in the implementation.
     */
    @Test
    public void testDoctorConstructorWithNonEmptyStrings() {
        Doctor user = new Doctor(NAME, EMAIL, PASSWORD, PHONE, SPECIALITY);
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(PHONE, user.getPhone());
        assertEquals(SPECIALITY, user.getSpecialty());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(user.getCreatedAt().isBefore(now) || user.getCreatedAt().isEqual(now));
        assertTrue(user.getUpdatedAt().isBefore(now) || user.getUpdatedAt().isEqual(now));
        assertEquals(user.getCreatedAt(), user.getUpdatedAt());
    }

}
