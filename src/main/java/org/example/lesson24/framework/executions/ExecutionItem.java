package org.example.lesson24.framework.executions;

import org.example.lesson24.framework.assertions.assertions.Assertion;

import java.lang.reflect.Method;

public class ExecutionItem<T> {
    private final Class<?> testClass;
    private final Method method;
    private final Assertion assertResult;

    public ExecutionItem(Class<?> testClass, Method method, Assertion assertResult) {
        this.testClass = testClass;
        this.method = method;
        this.assertResult = assertResult;
    }

    public Class<?> getTestClass() {
        return testClass;
    }

    public Method getMethod() {
        return method;
    }

    public Assertion getAssertResult() {
        return assertResult;
    }

    @Override
    public String toString() {
        return String.format("%40s | %s", method.getName(), " assertResult=" + assertResult);
    }
}
