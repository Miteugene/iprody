package org.example.lesson17;

import com.github.javafaker.Faker;
import org.example.lesson17.task1.Task1;
import org.example.lesson17.task2.Apple;
import org.example.lesson17.task2.Box;
import org.example.lesson17.task2.Orange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Lesson17 {
    public static void main(String[] args) {
        firstTask1();
        System.out.println("=======================================");
        firstTask2();
        System.out.println("=======================================");
        secondTask();
    }

    private static void firstTask1() {
        Task1<Integer> task1 = new Task1<>();

        Integer[] intArr = new Integer[10];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = i;
        }

        System.out.println("Before: " + Arrays.toString(intArr));
        task1.swapPairs(intArr);
        System.out.println("After:  " + Arrays.toString(intArr));
    }

    private static void firstTask2() {
        Faker faker = new Faker();

        Task1<String> task1 = new Task1<>();

        String[] strArr = new String[10];

        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = faker.lorem().word();
        }

        System.out.println("Before: " + strArr.getClass().getSimpleName() + ": " + Arrays.toString(strArr));
        ArrayList<String> list = task1.arrayToList(strArr);
        System.out.println("After:  " + list.getClass().getSimpleName() + ": " + list.toString());
    }

    private static void secondTask() {
        Random random = new Random();

        Box<Apple> boxOfApples = new Box<>();
        Box<Apple> anotherBoxOfApples = new Box<>();
        Box<Orange> boxOfOranges = new Box<>();

        int applesCount = random.nextInt(5, 10);
        int anotherApplesCount = random.nextInt(5, 10);
        int orangesCount = random.nextInt(5, 10);

        for (int i = 0; i < applesCount; i++) {
            boxOfApples.add(new Apple(random.nextFloat(0.9f, 1.2f)));
        }

        for (int i = 0; i < anotherApplesCount; i++) {
            anotherBoxOfApples.add(new Apple(random.nextFloat(0.9f, 1.2f)));
        }

        for (int i = 0; i < orangesCount; i++) {
            boxOfOranges.add(new Orange(random.nextFloat(1.0f, 1.5f)));
        }

        System.out.printf("Apples count: %d, weight: %f\n", boxOfApples.getFruitsCount(), boxOfApples.getWeight());
        System.out.printf("Oranges count: %d, weight: %f\n", boxOfOranges.getFruitsCount(), boxOfOranges.getWeight());
        System.out.printf("Compare result: %f\n", boxOfApples.compare(boxOfOranges));
        System.out.println("--------------------------------------------");

        System.out.printf("Apples count: %d, weight: %f\n", boxOfApples.getFruitsCount(), boxOfApples.getWeight());
        System.out.printf("Another Apples count: %d, weight: %f\n", anotherBoxOfApples.getFruitsCount(), anotherBoxOfApples.getWeight());
        boxOfApples.transfer(anotherBoxOfApples);
        System.out.printf("After Transferring. Apples count: %d, weight: %f\n", boxOfApples.getFruitsCount(), boxOfApples.getWeight());
        System.out.printf("After Transferring. Another Apples count: %d, weight: %f\n", anotherBoxOfApples.getFruitsCount(), anotherBoxOfApples.getWeight());
    }
}
