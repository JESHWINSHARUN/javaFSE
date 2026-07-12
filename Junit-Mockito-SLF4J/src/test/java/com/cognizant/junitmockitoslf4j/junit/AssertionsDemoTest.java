package com.cognizant.junitmockitoslf4j.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsDemoTest {

    AssertionsDemo a = new AssertionsDemo();

    @Test
    void testAssertEquals() {
        assertEquals(10, a.add(5, 5));
    }

    @Test
    void testAssertTrue() {
        assertTrue(a.isEven(8));
    }

    @Test
    void testAssertFalse() {
        assertFalse(a.isEven(7));
    }

    @Test
    void testAssertNotNull() {
        assertNotNull(a.getObject());
    }

    @Test
    void testAssertNull() {
        assertNull(a.getNullObject());
    }

    @Test
    void testAssertArrayEquals() {
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, a.getNumbers());
    }

    @Test
    void testAssertSame() {
        String s = a.getName();
        assertSame(s, s);
    }

    @Test
    void testAssertNotSame() {
        String s1 = new String("Java");
        String s2 = new String("Java");
        assertNotSame(s1, s2);
    }
}