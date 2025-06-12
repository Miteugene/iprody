package org.example.lesson27;

public class SomeMath {
    public static int sum(int a, int b)
    {
        return a + b;
    }

    public static float div(float a, float b)
    {
        return a / b;
    }

    public static double squareRoot(double number) {
        return Math.sqrt(number);
    }

    public static long calculateFibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number cannot be negative.");
        } else if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        long a = 0;
        long b = 1;

        for (int i = 2; i <= n; i++) {
            long nextFib = a + b;
            a = b;
            b = nextFib;
        }

        return b;
    }

    public static long calculateFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number cannot be negative.");
        } else if (n == 0) {
            return 1;
        }

        long result = 1;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        return result;
    }


}
