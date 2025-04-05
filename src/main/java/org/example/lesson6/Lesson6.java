package org.example.lesson6;

import java.util.Arrays;
import java.util.Random;

public class Lesson6 {
    public static void main(String[] args) {
        firstTask();
        System.out.println("\n===========================================");
        secondTask();
    }

    private static void firstTask() {
        Random random = new Random();
        int arrLen1 = random.nextInt(0, 10);
        int arrLen2 = random.nextInt(0, 10);

        int[] arr1 = new int[arrLen1];
        int[] arr2 = new int[arrLen2];

        for (int i = 0; i < arrLen1; i++) {
            arr1[i] = random.nextInt(0, 10);
        }
        for (int i = 0; i < arrLen2; i++) {
            arr2[i] = random.nextInt(0, 10);
        }

        System.out.printf(
                "Array 1: %s\nArray 2: %s\nMerged : %s",
                Arrays.toString(arr1),
                Arrays.toString(arr2),
                Arrays.toString(ArrayMerge.hande(arr1, arr2))
        );
    }

    private static void secondTask() {
        Random random = new Random();
        int arrLen = random.nextInt(5, 15);

        int[] arr = new int[arrLen];

        for (int i = 0; i < arrLen; i++) {
            arr[i] = random.nextInt(0, 10);
        }

        int[] arrBeforeSort = Arrays.copyOf(arr, arr.length);
        int iterations = ShakerSort.hande(arr);

        System.out.printf(
                "Array before sort: %s\nArray after sort: %s\nArray length: %d, squared length: %d\nIterations number: %d",
                Arrays.toString(arrBeforeSort),
                Arrays.toString(arr),
                arr.length,
                arr.length * arr.length,
                iterations
        );
    }
}
