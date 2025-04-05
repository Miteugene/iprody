package org.example.lesson8;

import java.util.Arrays;
import java.util.Random;

public class StringWorks {

    /**
     * Реализовать метод findSymbolOccurrence. Метод принимает в качестве параметров строку и символ.
     * Необходимо вычислить, сколько раз символ встречается в переданной строке и вернуть это числовое значение.
     *
     * @param str
     * @param symbol
     * @return
     */
    public static int findSymbolOccurrence(String str, char symbol) {
        if (str == null) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == symbol) {
                count++;
            }
        }

        return count;
    }

    /**
     * Реализовать метод findWordPosition. Метод принимает в качестве параметров две строки (source, target).
     * Необходимо выяснить, является ли target (подстрока) частью строки source.
     * Если да, тогда вернуть номер позиции (индекс) первого элемента подстроки в строке, иначе вернуть -1.
     *
     * @param source
     * @param target
     * @return
     */
    public static int findWordPosition(String source, String target) {
        if (source == null || target == null || source.length() < target.length()) {
            return -1;
        }

        // int index = source.indexOf(target); -- Да, можно так, но это неинтересно конечно же

        Random random = new Random();
        int indexOf = -1;

        // Выбираем способ поиска случайным образом
        if (random.nextInt(2) == 1) {
            // Тупо через цикл
            int i = 0;
            int j = 0;

            // Lorem ipsum dolor sit amet, consectetur adipiscing elit
            while (i < source.length()) {
                if (j == target.length()) {
                    indexOf = i - j;
                    break;
                }

                if (source.charAt(i) == target.charAt(j)) {
                    j++;
                } else {
                    j = 0;
                }
                i++;
            }
        } else {
            // А вот так повеселее
            String[] strArray = source.split(target);
            indexOf = strArray[0].length();
            indexOf = indexOf == source.length() ? -1 : indexOf;
        }

        return indexOf;
    }

    /**
     * Реализовать метод stringReverse. Метод принимает в качестве параметра строку.
     * Необходимо развернуть данную строку и вернуть измененный вариант.
     *
     * @param source
     * @return
     */
    public static String stringReverse(String source) {
        StringBuilder builder = new StringBuilder();

        for (int i = source.length() - 1; i >= 0; i--) {
            builder.append(source.charAt(i));
        }

        return builder.toString();
    }

    public static boolean isPalindrome(String source) {
        Random random = new Random();

        boolean isPalindrome = true;

        // Выбираем способ поиска случайным образом
        if (random.nextInt(2) == 1) {
            isPalindrome = source.equalsIgnoreCase(stringReverse(source));
        } else {
            // Ну и тупо через цикл

            int strLength = source.length();
            int half = strLength / 2;
            int rightIndex = strLength - 1;

            for (int i = 0; i < half; i++) {
                if (source.charAt(i) != source.charAt(rightIndex - i)) {
                    isPalindrome = false;
                    break;
                }
            }
        }

        return isPalindrome;
    }


}
