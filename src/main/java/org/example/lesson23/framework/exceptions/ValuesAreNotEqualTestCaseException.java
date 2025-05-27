package org.example.lesson23.framework.exceptions;

public class ValuesAreNotEqualTestCaseException extends RuntimeException {
    public ValuesAreNotEqualTestCaseException(int expected, int actual) {
        super(String.format("The values expected: %d and actual: %d are not equal", expected, actual));
    }
}
