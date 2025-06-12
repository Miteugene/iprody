package org.example.lesson26;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Lesson26GetSubArrayTest {

    private static Lesson26 somethingWithArrays;

    @BeforeAll
    public static void beforeTests() {
        somethingWithArrays = new Lesson26();
    }

    @Test
    void testGetSubArraySimple() {

        assertArrayEquals(
                new int[]{7, 8, 9},
                somethingWithArrays.getSubArray(
                        new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9},
                        6
                        )
        );
    }

    @Test
    void testGetSubArrayNull() {

        assertThrowsExactly(RuntimeException.class, () -> {
            somethingWithArrays.getSubArray(
                    null,
                    6
            );
        });
    }

    @Test
    void testGetSubArrayNumberNotFound() {

        assertThrowsExactly(RuntimeException.class, () -> {
            somethingWithArrays.getSubArray(
                    new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9},
                    11
            );
        });
    }
}
