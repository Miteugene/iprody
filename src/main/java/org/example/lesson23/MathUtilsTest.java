package org.example.lesson23;

import org.example.lesson23.framework.TestCase;
import org.example.lesson23.framework.annotations.AfterSuite;
import org.example.lesson23.framework.annotations.BeforeSuite;
import org.example.lesson23.framework.annotations.Test;

import java.util.Random;

public class MathUtilsTest extends TestCase {
    MathUtils mathUtils;
    Random random;

    @BeforeSuite
    public void runBefore() {
        System.out.println("MathUtilsTest start");
        mathUtils = new MathUtils();
        random = new Random();
    }

    @AfterSuite
    public void runAfter() {
        System.out.println("MathUtilsTest finish");
    }

    @Test(order = 2)
    public void testSum() {
        int a = random.nextInt();
        int b = random.nextInt();

        int expected = a + b;
        int actual = mathUtils.sum(a, b);

        this.assertEqual(expected, actual);
    }

    @Test(order = 3)
    public void testSub() {
        int a = random.nextInt();
        int b = random.nextInt();

        int expected = a - b;
        int actual = mathUtils.sub(a, b);

        this.assertEqual(expected, actual);
    }

    @Test(order = 1)
    public void testMult() {
        int a = random.nextInt();
        int b = random.nextInt();

        int expected = a * b;
        int actual = mathUtils.mult(a, b);

        this.assertEqual(expected, actual);
    }

    @Test(order = 123)
    public void testMultFailed() {
        int a = 2;
        int b = 2;

        int expected = 5;
        int actual = mathUtils.mult(a, b);

        this.assertEqual(expected, actual);
    }
}
