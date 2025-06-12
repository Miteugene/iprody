package org.example.lesson27;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SomeMathTest {

    private static Random random = new Random();

    @Test
    void testSum() {
        // arrange
        int a = random.nextInt();
        int b = random.nextInt();

        // act
        int actual = SomeMath.sum(a, b);

        // assert
        assertEquals(a + b, actual);
    }

    @Test
    void testDiv() {
        // arrange
        float a = 10;
        float b = 2;

        // act
        float actual = SomeMath.div(a, b);

        // assert
        assertEquals(a / b, actual);
    }

    @Test
    void squareRoot() {
        // arrange
        float a = 10;

        // act
        double actual = SomeMath.squareRoot(a);

        // assert
        assertEquals(Math.sqrt(a), actual);
    }

    @Test
    void testFibonacci() {
        // arrange
        int n = 10;

        // act
        long actual = SomeMath.calculateFibonacci(n);

        // assert
        assertEquals(55, actual);
    }

    @Test
    void testFactorial() {
        // arrange
        int n = 10;

        // act
        long actual = SomeMath.calculateFactorial(n);

        // assert
        assertEquals(3628800, actual);
    }

    @Test
    void testFibonacciHandledFail() {
        // arrange
        int n = -10;

        // act
        // assert
        assertThrowsExactly(IllegalArgumentException.class, () -> {
            long actual = SomeMath.calculateFibonacci(n);
        });
    }

    @Disabled("Тот самый тест с ошибкой")
    @Test
    void testSumUnhandledFail() {
        // arrange
        int a = random.nextInt();
        int b = random.nextInt();

        // act
        int actual = SomeMath.sum(a, b);

        // assert
        assertEquals(a + b + 1, actual);
    }

}
