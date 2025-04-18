package org.example.lesson12;

import com.github.javafaker.Faker;
import org.example.lesson12.exceptions.ArrayDataException;
import org.example.lesson12.exceptions.ArraySizeException;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lesson12 {
    public static void main(String[] args) {
        ArrayValueCalculator arrayValueCalculator = new ArrayValueCalculator();
        Scanner scanner = new Scanner(System.in);

        boolean isContinue;
        int sumResult;

        do {
            String[][] arr = getRandomArray();

            printArray(arr);

            try {
                sumResult = arrayValueCalculator.doCalc(arr);
                System.out.println("Sum result: " + sumResult);
            } catch (ArrayDataException | ArraySizeException e) {
                System.out.println("An error occurred: " + e.getClass().getName() + "; message: " + e.getMessage());
            }

            System.out.println("If you want to exit, enter: \"e\"");
            isContinue = !scanner.nextLine().trim().equals("e");
        } while (isContinue);
    }

    private static void printArray(String[][] arr) {
        for (String[] row : arr) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static String[][] getRandomArray() {
        Random random = new Random();
        Faker faker = new Faker();

        int m = 4;
        int n = random.nextInt(3, 5);

        String[][] arr = new String[m][n];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt(0, 100) < 10
                        ? faker.lorem().word()
                        : Integer.toString(random.nextInt(1, 15));
            }
        }

        return arr;
    }
}
