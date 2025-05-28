package org.example.lesson24.framework.runner;

import org.example.lesson24.framework.annotations.Test;
import org.example.lesson24.framework.assertions.assertions.AssertionEmptyResult;
import org.example.lesson24.framework.assertions.exceptions.AssertException;
import org.example.lesson24.framework.executions.Execution;
import org.example.lesson24.framework.executions.ExecutionItem;
import org.example.lesson24.framework.printers.Printer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExtendedTestApplicationRunner implements Runner {
    private List<Class<?>> testClasses;
    private Printer printer;

    public ExtendedTestApplicationRunner(List<Class<?>> testClasses, Printer printer) {
        this.testClasses = testClasses;
        this.printer = printer;
    }

    @Override
    public void run() {
        var executions = testClasses.stream()
                .map(this::processTestClass)
                .toList();

        printer.write(executions);
    }

    private Object testClassInstance(Class<?> testClass) {
        try {
            Constructor<?> constructor = testClass.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new IllegalArgumentException("Test class must have constructor without parameters: " + testClass.getName());
        }
    }

    Execution processTestClass(Class<?> testClass) {
        Object testClassObject = testClassInstance(testClass);

        LocalDateTime startDateTime = LocalDateTime.now();
        List<ExecutionItem> executionItems = new ArrayList<>();
        for (Method method : testClass.getMethods()) {
            if (!method.isAnnotationPresent(Test.class)) {
                continue;
            }

            executionItems.add(invokeTestMethod(method, testClassObject));
        }

        LocalDateTime endDateTime = LocalDateTime.now();
        return new Execution(testClass, executionItems, startDateTime, endDateTime);
    }

    ExecutionItem invokeTestMethod(Method method, Object testClassObject) {
        if (method.getParameterCount() > 0) {
            throw new IllegalArgumentException("Method with parameter cannot be test: " + method.getName());
        }

        try {
            method.invoke(testClassObject);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException invocationTargetException) {
            if (invocationTargetException.getCause() instanceof AssertException assertException)
                return new ExecutionItem(testClassObject.getClass(), method, assertException.getAssertResult());

            throw new RuntimeException(invocationTargetException);
        }

        return new ExecutionItem(testClassObject.getClass(), method, AssertionEmptyResult.EMPTY_SUCCESS);
    }

}