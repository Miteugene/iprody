package org.example.lesson26;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Lesson26IsOnlyTargetNumbersTest {

    private static Lesson26 somethingWithArrays;

    @BeforeAll
    public static void beforeTests() {
        somethingWithArrays = new Lesson26();
    }

    @Test
    void testIsOnlyFromTargetNumbersSimple() {
        assertTrue(
                somethingWithArrays.isOnlyFromTargetNumbers(
                        new int[] {1, 1, 1, 4, 4, 4, 1, 1, 1},
                        new int[] {1, 4}
                )
        );
    }

    @Test
    void testIsOnlyFromTargetNumbersFalseCondition() {
        assertFalse(
                somethingWithArrays.isOnlyFromTargetNumbers(
                        new int[] {1, 1, 1, 4, 4, 4, 1, 1, 1},
                        new int[] {1, 4, 3}
                )
        );
    }

    @Test
    void testIsOnlyFromTargetNumbersNull() {
        assertThrowsExactly(RuntimeException.class, () -> {
            somethingWithArrays.isOnlyFromTargetNumbers(
                    null,
                    new int[] {1, 4, 3}
            );
        });
    }
}
