package org.example.lesson24.program.test;

import org.example.lesson24.framework.annotations.Test;
import org.example.lesson24.framework.assertions.Assert;
import org.example.lesson24.program.Calculator;

public class CalculatorTest {
    @Test
    public void testSumOfPositives() {
        Assert.equal(5, Calculator.sum(3, 2));
    }

    @Test
    public void testSumWithZero() {
        Assert.equal(4, Calculator.sum(4, 0));
    }

    @Test
    public void testSumOfNegatives() {
        Assert.equal(-9, Calculator.sum(-4, -5));
    }

    @Test
    public void testSumOfPositiveAndNegative() {
        Assert.equal(-1, Calculator.sum(4, -5));
    }

    @Test
    public void testSumOfPositiveAndNegativeWrong() {
        Assert.equal(-1, Calculator.sum(3, -5));
    }

    @Test
    public void testSequenceOfOperation() {
        var result = Calculator.sum(2, 5);
    }

    @Test
    public void testStringContains() {
        String value = "some other value";

        Assert.contains(value, "other");
    }

    @Test
    public void testStringContainsWrong() {
        String value = "some other value";

        Assert.contains(value, "others");
    }

    @Test
    public void testArrayContainsStrictMode() {
        Integer[] array = new Integer[]{5, 9, 1, 2, 3, 10};
        Integer[] subArray = new Integer[]{1, 2, 3};

        Assert.contains(array, subArray, true);
    }

    @Test
    public void testArrayContainsStrictModeWrong() {
        Integer[] array = new Integer[]{5, 9, 1, 2, 3, 10};
        Integer[] subArray = new Integer[]{1, 2, 3, 7};

        Assert.contains(array, subArray, true);
    }

    @Test
    public void testArrayContainsNotStrictMode() {
        Integer[] array = new Integer[]{5, 9, 1, 2, 3, 10};
        Integer[] subArray = new Integer[]{9, 10, 2};

        Assert.contains(array, subArray, false);
    }

    @Test
    public void testArrayContainsNotStrictModeWrong() {
        Integer[] array = new Integer[]{5, 9, 1, 2, 3, 10};
        Integer[] subArray = new Integer[]{9, 10, 2, 7};

        Assert.contains(array, subArray, false);
    }
}
