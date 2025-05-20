package org.example.lesson21;

import java.util.Arrays;

public class ValueCalculator {

    public void doCalc(int arraySize, int threadsCount) {
        long startTotalTime = System.currentTimeMillis();

        // Инициализируем основной массив
        double[] originalArray = initArray(arraySize);

        //System.out.println("Original: \n" + Arrays.toString(originalArray));

        // Определяем размерность подмассивов
        int baseSize = arraySize / threadsCount;
        int remainder = arraySize % threadsCount;

        double[][] arraysOfArrays = new double[threadsCount][];
        Thread[] threads = new Thread[threadsCount];

        // Создаем threadsCount массивов из основного массива и threadsCount тредов для обработки подмассивов
        for (int i = 0, offset = 0; i < threadsCount; i++) {
            int currentSize = baseSize + (i < remainder ? 1 : 0);
            arraysOfArrays[i] = new double[currentSize];
            double[] currentArray = arraysOfArrays[i];

            System.arraycopy(originalArray, offset, currentArray, 0, currentSize);
            offset += currentSize;

            threads[i] = new Thread(() -> this.handleArray(currentArray));
        }

        //System.out.println("Sub arrays:");
        //Arrays.stream(arraysOfArrays).forEach((arr) -> System.out.println(Arrays.toString(arr)));

        long startThreadsTime = System.currentTimeMillis();

        // Запускаем все треды
        for (Thread thread : threads) {
            thread.start();
        }

        // Ждем окончания выполнения всех тредов
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println("Handled Sub arrays:");
        //Arrays.stream(arraysOfArrays).forEach((arr) -> System.out.println(Arrays.toString(arr)));

        long finishThreadsTime = System.currentTimeMillis() - startThreadsTime;

        // Сливаем все подмассивы в исходный массив
        for (int i = 0, offset = 0; i < arraysOfArrays.length; i++) {
            double[] currentArray = arraysOfArrays[i];
            System.arraycopy(currentArray, 0, originalArray, offset, currentArray.length);
            offset += currentArray.length;
        }

        //System.out.println("Result: \n" + Arrays.toString(originalArray));

        System.out.printf(
                "Threads count: %d\nArray size: %d\nSub array size: %d\nTotal Time: %d\nThreads time: %d\n",
                threadsCount,
                arraySize,
                baseSize,
                System.currentTimeMillis() - startTotalTime,
                finishThreadsTime
        );
    }

    private void handleArray(double[] arrayOfNumbers) {
        for (int i = 0; i < arrayOfNumbers.length; i++) {
            arrayOfNumbers[i] = arrayOfNumbers[i] * Math.sin(0.2f + (double) i / 5) * Math.cos(0.2f + (double) i / 5) * Math.cos(0.4f + (double) i / 2);
        }
    }

    private double[] initArray(int arraySize) {
        double[] arr = new double[arraySize];
        Arrays.fill(arr, 1);
        return arr;
    }
}
