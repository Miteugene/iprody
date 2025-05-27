package org.example.lesson23.framework;

import org.example.lesson23.framework.annotations.AfterSuite;
import org.example.lesson23.framework.annotations.BeforeSuite;
import org.example.lesson23.framework.annotations.Test;
import org.example.lesson23.framework.exceptions.AnnotationException;
import org.example.lesson23.framework.exceptions.ValuesAreNotEqualTestCaseException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestRunner {
    public static <T> void start(Class<T> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var classObject = clazz.getDeclaredConstructor().newInstance();

        Method[] methods = clazz.getDeclaredMethods();

        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;
        ArrayList<Method> testMethods = new ArrayList<>();

        int beforeSuiteMethodsCount = 0;
        int afterSuiteMethodsCount = 0;

        for (Method method: methods) {
            int annotationsCountInOneMethod = 0;

            if (method.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuiteMethod = method;
                beforeSuiteMethodsCount++;
                annotationsCountInOneMethod++;
            }

            if (method.isAnnotationPresent(AfterSuite.class)) {
                afterSuiteMethod = method;
                afterSuiteMethodsCount++;
                annotationsCountInOneMethod++;
            }

            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
                annotationsCountInOneMethod++;
            }

            if (annotationsCountInOneMethod > 1) {
                throw new AnnotationException("More than one annotation specified for one method");
            }
        }

        if (beforeSuiteMethodsCount > 1) {
            throw new AnnotationException("Only one method can be BeforeSuite");
        }

        if (afterSuiteMethodsCount > 1) {
            throw new AnnotationException("Only one method can be AfterSuite");
        }

        List<Method> sortedMethodList = testMethods
                .stream()
                .sorted(Comparator.comparingInt(TestRunner::getMethodOrder))
                .toList();

        if (beforeSuiteMethod != null) {
            beforeSuiteMethod.invoke(classObject);
        }

        runTests(sortedMethodList, classObject);

        if (afterSuiteMethod != null) {
            afterSuiteMethod.invoke(classObject);
        }
    }

    private static <T> void runTests(List<Method> testCases, T classObject) throws IllegalAccessException {
        for (Method testCase: testCases) {
            System.out.printf(
                    "Test: %s started with order: %d -- ",
                    testCase.getName(),
                    testCase.getAnnotation(Test.class).order()
            );
            try {
                testCase.invoke(classObject);
            } catch (InvocationTargetException e) {
                System.out.println("FAILED");
                e.printStackTrace();
                continue;
            }

            System.out.println("OK");
        }
    }

    private static int getMethodOrder(Method method) {
        return method.getAnnotation(Test.class).order();
    }

}
