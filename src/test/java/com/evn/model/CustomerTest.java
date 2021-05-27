package com.evn.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {

    private Customer customerUnderTest;

    @BeforeEach
    void setUp() {
        customerUnderTest = new Customer();
    }

    @Test
    void testEquals() {
        // Setup

        // Run the test
        final boolean result = customerUnderTest.equals("o");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testHashCode() {
        // Setup

        // Run the test
        final int result = customerUnderTest.hashCode();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = customerUnderTest.toString();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCanEqual() {
        // Setup

        // Run the test
        final boolean result = customerUnderTest.canEqual("other");

        // Verify the results
        assertTrue(result);
    }
}
