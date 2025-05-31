package org.example.lesson24.framework.assertions.assertions;

public class AssertionEqual<T> extends Assertion {
    private final T expected;
    private final T actual;

    public AssertionEqual(T expected, T actual, boolean success) {
        super(success);
        this.expected = expected;
        this.actual = actual;
    }

    public T getExpected() {
        return expected;
    }

    public T getActual() {
        return actual;
    }

    @Override
    public String toString() {
        return String.format("expected: %s, actual: %s", expected, actual);
    }
}
