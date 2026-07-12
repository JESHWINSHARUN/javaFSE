package com.cognizant.junitmockitoslf4j.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator c = new Calculator();

    @Test
    void testAddPositiveNumbers() {
        assertEquals(8, c.add(5, 3));
    }

    @Test
    void testAddNegativeNumbers() {
        assertEquals(-8, c.add(-5, -3));
    }

    @Test
    void testAddZero() {
        assertEquals(5, c.add(5, 0));
    }
}