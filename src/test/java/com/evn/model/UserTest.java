package com.evn.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    private User userUnderTest;

    @BeforeEach
    void setUp() {
        userUnderTest = new User();
    }

    @Test
    void testEquals() {
        // Setup

        // Run the test
        final boolean result = userUnderTest.equals("o");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = userUnderTest.hashCode();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = userUnderTest.toString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = userUnderTest.canEqual("other");

        // Verify the results
        assertTrue(result);
    }
}
