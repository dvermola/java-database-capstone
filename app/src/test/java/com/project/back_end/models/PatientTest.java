package com.project.back_end.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest  {

    private static final String NAME = "testuser";
    private static final String PASSWORD = "testpass";
    private static final String EMAIL = "test@example.com";
    private static final String PHONE = "1234567890";
    private static final String ADDRESS = "123 Main St";

    /**
     * Test the Patient default constructor
     */
    @Test
    public void testPatientDefaultConstructor() {
        Patient user = new Patient();
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
        assertTrue(user.isActive());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(user.getCreatedAt().isBefore(now) || user.getCreatedAt().isEqual(now));
        assertTrue(user.getUpdatedAt().isBefore(now) || user.getUpdatedAt().isEqual(now));
        assertEquals(user.getCreatedAt(), user.getUpdatedAt());
    }

    /**
     * Tests the Patient constructor with empty strings for user's name and password.
     * This verifies that the constructor allows empty strings without throwing exceptions,
     * as there are no explicit checks for empty strings in the implementation.
     */
    @Test
    public void testPatientConstructorWithEmptyStrings() {
        Patient user = new Patient("", "", "", "", "");
        assertEquals("", user.getName());
        assertEquals("", user.getEmail());
        assertEquals("", user.getPassword());
        assertEquals("", user.getPhone());
        assertEquals("", user.getAddress());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
        assertTrue(user.isActive());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(user.getCreatedAt().isBefore(now) || user.getCreatedAt().isEqual(now));
        assertTrue(user.getUpdatedAt().isBefore(now) || user.getUpdatedAt().isEqual(now));
        assertEquals(user.getCreatedAt(), user.getUpdatedAt());
    }

    /**
     * Tests the Patient constructor with non-empty strings for user's name and password.
     * This verifies that the constructor allows empty strings without throwing exceptions,
     * as there are no explicit checks for empty strings in the implementation.
     */
    @Test
    public void testPatientConstructorWithNonEmptyStrings() {
        Patient user = new Patient(NAME, EMAIL, PASSWORD, PHONE, ADDRESS);
        assertEquals(NAME, user.getName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(PHONE, user.getPhone());
        assertEquals(ADDRESS, user.getAddress());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(user.getCreatedAt().isBefore(now) || user.getCreatedAt().isEqual(now));
        assertTrue(user.getUpdatedAt().isBefore(now) || user.getUpdatedAt().isEqual(now));
        assertEquals(user.getCreatedAt(), user.getUpdatedAt());
    }

}
