package org.example.lesson22;

import org.example.lesson22.task1.ThreadSafeList;
import org.example.lesson22.task2.PetrolStation;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lesson22 {
    public static void main(String[] args) {
        task1();
        System.out.println("==========================");
        task2();
    }

    private static void task1() {
        Random random = new Random();
        ThreadSafeList<Integer> threadSafeList = new ThreadSafeList<>();

        int threadsCount = 10;
        int taskCount = 20;

        try (ExecutorService executorService = Executors.newFixedThreadPool(threadsCount)) {
            for (int i = 0; i < taskCount; i++) {
                executorService.execute(() -> {
                    String threadName = Thread.currentThread().getName();

                    int randomValue = random.nextInt(0, 100);

                    if (randomValue >= 0 && randomValue < 60) {
                        int value = random.nextInt(0, 100);
                        threadSafeList.add(value);
                        System.out.println(threadName + " added value: " + value);
                    } else if (randomValue >= 60 && randomValue < 80) {
                        if (threadSafeList.size() > 0) {
                            int index = random.nextInt(0, threadSafeList.size());
                            int value = threadSafeList.remove(index);
                            System.out.println(threadName + " removed value: " + value + " by index: " + index);
                        } else {
                            System.out.println(threadName + ": Tried to remove but List is empty");
                        }
                    } else if (randomValue >= 80) {
                        if (threadSafeList.size() > 0) {
                            int index = random.nextInt(0, threadSafeList.size());
                            int value = threadSafeList.get(index);
                            System.out.println(threadName + " got value: " + value + " by index: " + index);
                        } else {
                            System.out.println(threadName + ": Tried to get but List is empty");
                        }
                    }
                });
            }
            executorService.shutdown();
        }
    }

    private static void task2() {
        Random random = new Random();
        PetrolStation petrolStation = new PetrolStation(1000, 3);

        int carsCount = 100;

        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < carsCount; i++) {
                executorService.execute(() -> {
                    petrolStation.doTank(random.nextDouble(10, 50));
                });
            }
            executorService.shutdown();
        }

    }
}
