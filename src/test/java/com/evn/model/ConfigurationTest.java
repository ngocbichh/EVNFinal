package com.evn.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigurationTest {

    private Configuration configurationUnderTest;

    @BeforeEach
    void setUp() {
        configurationUnderTest = new Configuration();
    }

    @Test
    void testEquals() {
        // Setup

        // Run the test
        final boolean result = configurationUnderTest.equals("o");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = configurationUnderTest.hashCode();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = configurationUnderTest.toString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = configurationUnderTest.canEqual("other");

        // Verify the results
        assertTrue(result);
    }
}
