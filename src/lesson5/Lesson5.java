package lesson5;

import lesson5.first.MyArrayList;
import lesson5.third.MyLinkedList;

import java.util.Random;

public class Lesson5 {
    public static void main(String[] args) {
        firstTask();
        secondTask();
        thirdTask();
    }

    /**
     * 1. Инициализация списка.
     * В качестве параметров метод принимает размер списка и инициализирует его.
     * Возвращающемся значением должен быть одномерный целочисленный массив указанной длины.
     *
     * 2. Добавление в список.
     * В качестве параметров метод принимает проинициализированный одномерный целочисленный массив и новое значение.
     * Данное значение должен быть добавлено в конец списка.
     *
     * 3. Получение значения по индексу.
     * В качестве параметра метод принимает проинициализированный одномерный целочисленный массив.
     * Значение соответствующее указанному индексу должно быть получено из списка и возвращено в качестве возвращаемого значения метода.
     *
     * 4. Получение длины списка.
     * В качестве параметра метод принимает проинициализированный одномерный целочисленный массив.
     * Значение соответствующее длине списка должно быть возвращено в качестве возвращаемого значения метода.
     */
    private static void firstTask() {
        Random random = new Random();

        // 1. Инициализация списка.
        MyArrayList myArrayList = new MyArrayList();

        System.out.println("\n=========================\nFirst Task");
        System.out.print("After init: ");
        System.out.println(myArrayList.toString());
        System.out.println();

        // 2. Добавление в список.
        for (int i = 0; i < 10; i++) {
            int randomInt = random.nextInt(0, 10);
            myArrayList.push(randomInt);

            System.out.printf(
                "After push iteration %d, randomInt: %d, List: %s\n",
                i,
                randomInt,
                myArrayList.toString()
            );
        }

        // 3. Получение значения по индексу.
        for (int i = 0; i < 4; i++) {
            int randomIndex = random.nextInt(0, myArrayList.length - 1);

            System.out.printf(
                "Get element with index %d: %s\n",
                randomIndex,
                myArrayList.get(randomIndex).toString()
            );
        }

        // 4. Получение длины списка.
        System.out.printf("List length: %d\nAllocated length: %d\n", myArrayList.length, myArrayList.getAllocatedLength());
    }

    /**
     * 1. Инициализация очереди.
     * В качестве параметров метод принимает размер очереди и инициализирует ее.
     * Возвращающемся значением должен быть одномерный целочисленный массив указанной длины.
     *
     * 2. Добавление в очередь.
     * В качестве параметров метод принимает проинициализированный одномерный целочисленный массив и новое значение.
     * Данное значение должен быть добавлено очередь.
     *
     * 3. Получение из очереди.
     * В качестве параметра метод принимает проинициализированный одномерный целочисленный массив.
     * Ближайшее значение (согласно принципу FIFO) должно быть извлечено из очереди и возвращено в качестве
     * возвращаемого значения метода.
     */
    private static void secondTask() {
        // Ну я сделал третью задачу на двусвязном списке, так что просто использую его же
        Random random = new Random();

        // 1. Инициализация очереди.
        MyLinkedList queue = new MyLinkedList();

        System.out.println("\n=========================\nSecond Task");
        System.out.print("After init: ");
        System.out.println(queue.toString());
        System.out.println();

        // 2. Добавление в очередь.
        for (int i = 0; i < 10; i++) {
            int randomInt = random.nextInt(0, 10);
            queue.pushLast(randomInt);

            System.out.printf(
                "After pushLast iteration %d, randomInt: %d, List: %s\n",
                i,
                randomInt,
                queue.toString()
            );
        }

        // 3. Получение из очереди.
        for (int i = 0; i < 10; i++) {
            Object userObject = queue.pullFirst();

            System.out.printf(
                "After pullFirst iteration %d, Pulled Object: %s, List: %s\n",
                i,
                userObject == null ? "null" : userObject.toString(),
                queue.toString()
            );
        }
    }

    /**
     * 1. Инициализация очереди.
     * В качестве параметров метод принимает размер очереди и инициализирует ее.
     * Возвращающемся значением должен быть одномерный целочисленный массив указанной длины.
     * (сделаю универсальный вариант)
     *
     * 2. Добавление в голову очереди.
     * В качестве параметров метод принимает проинициализированный одномерный целочисленный массив и новое значение.
     * Данное значение должен быть добавлено в голову очереди.
     *
     * 3. Получение из головы очереди.
     * В качестве параметра метод принимает проинициализированный одномерный целочисленный массив.
     * Ближайшее значение должно быть извлечено из головы очереди и возвращено в качестве возвращаемого значения метода.
     *
     * 4. Добавление в хвост очереди.
     * В качестве параметров метод принимает проинициализированный одномерный целочисленный массив и новое значение.
     * Данное значение должен быть добавлено в хвост очереди.
     *
     * 5. Получение из хвоста очереди.
     * В качестве параметра метод принимает проинициализированный одномерный целочисленный массив.
     * Ближайшее значение должно быть извлечено из хвоста очереди и возвращено в качестве возвращаемого значения метода.
     */
    private static void thirdTask() {
        Random random = new Random();

        // 1. Инициализация очереди.
        MyLinkedList myLinkedList = new MyLinkedList();

        System.out.println("\n=========================\nThird Task");
        System.out.print("After init: ");
        System.out.println(myLinkedList.toString());
        System.out.println();

        // 2. Добавление в голову очереди.
        for (int i = 0; i < 10; i++) {
            int randomInt = random.nextInt(0, 10);
            myLinkedList.pushFirst(randomInt);

            System.out.printf(
                "After pushFirst iteration %d, randomInt: %d, List: %s\n",
                i,
                randomInt,
                myLinkedList.toString()
            );
        }

        System.out.println();

        // 3. Получение из головы очереди.
        for (int i = 0; i < 5; i++) {
            Object userObject = myLinkedList.pullFirst();

            System.out.printf(
                "After pullFirst iteration %d, Pulled Object: %s, List: %s\n",
                i,
                userObject == null ? "null" : userObject.toString(),
                myLinkedList.toString()
            );
        }

        System.out.println();

        // 4. Добавление в хвост очереди.
        for (int i = 0; i < 5; i++) {
            int randomInt = random.nextInt(0, 10);
            myLinkedList.pushLast(randomInt);

            System.out.printf(
                "After pushLast iteration %d, randomInt: %d, List: %s\n",
                i,
                randomInt,
                myLinkedList.toString()
            );
        }

        System.out.println();

        // А еще можно сделать get по индексу
        int getIndex = 3;
        Object gettedUserObject = myLinkedList.get(getIndex);
        System.out.printf(
            "Get element with index %d: %s\n",
            getIndex,
            gettedUserObject == null ? "null" : gettedUserObject.toString()
        );
        // Или put по индексу
        int putIndex = 6;
        int putValue = 123;
        myLinkedList.put(putIndex, putValue);
        System.out.printf(
            "After put by index %d, value: %d, List: %s\n",
            putIndex,
            putValue,
            myLinkedList.toString()
        );

        System.out.println();

        // 5. Получение из хвоста очереди.
        for (int i = 0; i < 11; i++) {
            Object userObject = myLinkedList.pullLast();

            System.out.printf(
                "After pullLast iteration %d, Pulled Object: %s, List: %s\n",
                i,
                userObject == null ? "null" : userObject.toString(),
                myLinkedList.toString()
            );
        }
    }
}
