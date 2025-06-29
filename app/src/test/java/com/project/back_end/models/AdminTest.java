package com.project.back_end.models;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    /**
     * Test the Admin default constructor
     */
    @Test
    public void testAdminDefaultConstructor() {
        Admin user = new Admin();
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(user.getCreatedAt().isBefore(now) || user.getCreatedAt().isEqual(now));
        assertTrue(user.getUpdatedAt().isBefore(now) || user.getUpdatedAt().isEqual(now));
        assertEquals(user.getCreatedAt(), user.getUpdatedAt());
    }

    /**
     * Tests the Admin constructor with empty strings for username and password.
     * This verifies that the constructor allows empty strings without throwing exceptions,
     * as there are no explicit checks for empty strings in the implementation.
     */
    @Test
    public void testAdminConstructorWithEmptyStrings() {
        Admin admin = new Admin("", "");
        assertEquals("", admin.getUsername());
        assertEquals("", admin.getPassword());
        assertNotNull(admin.getCreatedAt());
        assertNotNull(admin.getUpdatedAt());
    }

    /**
     * Test case for Admin constructor
     * Verifies that the Admin object is created with correct username and password,
     * and that createdAt and updatedAt fields are initialized to the current time.
     */
    @Test
    public void testAdminConstructor() {
        String username = "testuser";
        String password = "testpass";

        Admin admin = new Admin(username, password);

        assertNotNull(admin);
        assertEquals(username, admin.getUsername());
        assertEquals(password, admin.getPassword());
        assertNotNull(admin.getCreatedAt());
        assertNotNull(admin.getUpdatedAt());

        LocalDateTime now = LocalDateTime.now();
        assertTrue(admin.getCreatedAt().isBefore(now) || admin.getCreatedAt().isEqual(now));
        assertTrue(admin.getUpdatedAt().isBefore(now) || admin.getUpdatedAt().isEqual(now));
    }

}
