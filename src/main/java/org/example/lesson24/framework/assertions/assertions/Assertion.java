package org.example.lesson24.framework.assertions.assertions;

public abstract class Assertion {
    private final boolean success;

    public Assertion(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}