package org.example.lesson12.exceptions;

public class ArraySizeException extends Exception {
    public ArraySizeException(int expectedArraySize) {
        super("Expected array size: " + expectedArraySize + "x" + expectedArraySize);
    }
}
