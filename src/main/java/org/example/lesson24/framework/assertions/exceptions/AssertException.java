package org.example.lesson24.framework.assertions.exceptions;

import org.example.lesson24.framework.assertions.assertions.Assertion;

public class AssertException extends RuntimeException {
    private final Assertion assertResult;

    public AssertException(Assertion assertResult) {
        this.assertResult = assertResult;
    }

    public Assertion getAssertResult() {
        return assertResult;
    }

    @Override
    public String getMessage() {
        return assertResult.toString();
    }
}
