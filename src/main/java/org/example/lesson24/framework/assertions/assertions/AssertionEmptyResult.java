package org.example.lesson24.framework.assertions.assertions;

public class AssertionEmptyResult extends Assertion {
    public static final AssertionEmptyResult EMPTY_SUCCESS = new AssertionEmptyResult(true);

    private AssertionEmptyResult(boolean success) {
        super(success);
    }

    @Override
    public String toString() {
        return "EXECUTED";
    }
}
