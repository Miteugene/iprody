package org.example.lesson23.framework;

import org.example.lesson23.framework.exceptions.ValuesAreNotEqualTestCaseException;

public abstract class TestCase {
    public void assertEqual(int expected, int actual) {
        if (expected != actual) {
            throw new ValuesAreNotEqualTestCaseException(expected, actual);
        }
    }
}
