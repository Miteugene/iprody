package org.example.lesson24.framework.assertions.assertions;

import java.util.Arrays;

public class AssertionArrayContains<T> extends Assertion {
    private final int STRING_LENGTH_LIMIT = 15;

    private final T[] array;
    private final T[] subArray;
    private final boolean strictOrder;

    public AssertionArrayContains(T[] array, T[] subArray, boolean strictOrder, boolean success) {
        super(success);
        this.array = array;
        this.subArray = subArray;
        this.strictOrder = strictOrder;
    }

    @Override
    public String toString() {
        return String.format(
                isSuccess()
                        ? "Input array %s contains subarray %s, strict order mode: %b"
                        : "Input array %s doesn't contains subarray %s, strict order mode: %b",
                normalize(array),
                normalize(subArray),
                this.strictOrder
        );
    }

    private String normalize(T[] arr) {
        String str = Arrays.stream(arr)
                .map(Object::toString)
                .toString();

        return str.length() > STRING_LENGTH_LIMIT ? str.substring(0, STRING_LENGTH_LIMIT) + "..." : str;
    }
}
