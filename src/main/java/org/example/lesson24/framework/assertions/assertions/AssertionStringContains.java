package org.example.lesson24.framework.assertions.assertions;

public class AssertionStringContains extends Assertion {
    private final int STRING_LENGTH_LIMIT = 15;

    private final String str;
    private final String subStr;

    public AssertionStringContains(String str, String subStr, boolean success) {
        super(success);
        this.str = str;
        this.subStr = subStr;
    }

    @Override
    public String toString() {
        String format = isSuccess()
            ? "Input %s contains string %s"
            : "Input %s doesn't contains string %s";

        return String.format(format, normalize(str), normalize(subStr));
    }

    private String normalize(String str) {
        return '"' + (str.length() > STRING_LENGTH_LIMIT ? str.substring(0, STRING_LENGTH_LIMIT) + "..." : str) + '"';
    }
}
