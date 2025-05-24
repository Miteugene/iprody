package org.example.lesson23;

import org.example.lesson23.framework.TestRunner;

public class Lesson23 {
    public static void main(String[] args) {
        try {
            TestRunner.start(MathUtilsTest.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
