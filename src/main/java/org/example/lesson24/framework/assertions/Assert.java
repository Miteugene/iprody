package org.example.lesson24.framework.assertions;

import org.example.lesson24.framework.assertions.assertions.AssertionArrayContains;
import org.example.lesson24.framework.assertions.assertions.AssertionEqual;
import org.example.lesson24.framework.assertions.assertions.AssertionStringContains;
import org.example.lesson24.framework.assertions.exceptions.AssertException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Assert {

    public static <T> void equal(T expected, T actual) {
        throw new AssertException(
                new AssertionEqual<>(
                        expected,
                        actual,
                        Objects.equals(expected, actual)
                )
        );
    }

    public static void contains(String str, String subStr) {
        boolean success = str != null && subStr != null && str.contains(subStr);
        throw new AssertException(
                new AssertionStringContains(
                        str,
                        subStr,
                        success
                )
        );
    }

    // Можно будет проверять строгое вхождение массива с учетом порядка или просто наличие нужных значений
    public static <T> void contains(T[] array, T[] subArray, boolean strictOrder) {
        boolean success = true;

        // Простая проверка на базовые параметры
        if (array == null || subArray == null || subArray.length == 0 || subArray.length > array.length) {
            success = false;
        } else if (strictOrder) {
            // Если нам важен порядок вхождения
            // Как ленивый вариант можно было бы преобразовать оба массива в строку и выполнить простой contains =), но вдруг там большие объекты

            boolean match = true;

            for (int i = 0; i <= array.length - subArray.length; i++) {
                match = true;

                for (int j = 0; j < subArray.length; j++) {
                    if (!array[i + j].equals(subArray[j])) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    break;
                }
            }

            success = match;
        } else {
            // Если порядок вхождения не важен
            List<T> arrayList = Arrays.asList(array);
            for (T item : subArray) {
                if (!arrayList.contains(item)) {
                    success = false;
                    break;
                }
            }
        }

        throw new AssertException(
                new AssertionArrayContains(
                        array,
                        subArray,
                        strictOrder,
                        success
                )
        );
    }

    public static <T> void equalRecursively(T expected, T actual) {
        /*
            Если честно, не пойму зачем нужен этот метод.
            По сути получится достаточно сложная реализация в где мы должны будем пройти по полям объекта,
            которые могут быть другим объектом, мапой, коллекцией, массивом, бесконечно углублясь в рекурсию при этом.
            При этом нужно учитывать какие-нибудь возможные циклы в виде перекрестных ссылок на объекты друг друга.
            Это может быть и внутри одного объекта, это может быть и между объектами.
            Кажется что реализация этого метода будет сложнее чем реализация этого фреймворка тестирования.
         */
    }
}
