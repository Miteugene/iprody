package org.example.lesson18;

import com.github.javafaker.Faker;

import java.util.Collection;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lesson18 {
    public static void main(String[] args) {
        Random random = new Random();
        Faker faker = new Faker();

        System.out.println("========================================");
        task1();

        System.out.println("========================================");
        Collection<Integer> intCollection = Stream.generate(() -> random.nextInt(1, 1000))
                .limit(10)
                .toList();
        task2(intCollection, num -> num % 2 == 0)
                .forEach(System.out::println);

        System.out.println("========================================");
        Collection<String> strCollection = Stream.generate(() -> faker.lorem().word())
                .limit(10)
                .toList();
        System.out.println(task3(strCollection, word -> word.length() > 5));

        System.out.println("========================================");
        System.out.println(task4(intCollection, OrderEnum.DESC));

        System.out.println("========================================");
        System.out.println(task5(5));

        System.out.println("========================================");
        task6();
    }

    /**
     * Необходимо реализовать метод генерирующий список из 100 случайных чисел в диапазоне от 1 до 1000.
     * Далее, данный метод должен найти топ-10 минимальных значений, затем исключить повторяющиеся значения,
     * и в конечном итоге отсортировать оставшиеся числа в порядке убывания.
     * Результат выполнения вывести в консоль.
     */
    private static void task1() {
        Random random = new Random();

        Stream.generate(() -> random.nextInt(1, 1000))
                .limit(100)
                .sorted()
                .limit(10)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    /**
     * Необходимо реализовать метод принимающий в качестве аргумента обобщенный Collection и обобщенный Predicat.
     * Метод должен выполнить фильтрацию переданной коллекции на основании предиката и в качестве результата
     * выполнения необходимо вернуть новый отфильтрованный экземпляр коллекции.
     *
     * @param collection
     * @param predicate
     * @return
     * @param <T>
     */
    private static <T> Collection<T> task2(Collection<T> collection, Predicate<T> predicate) {
        return collection.stream()
                .filter(predicate)
                .toList();
    }

    /**
     * Необходимо реализовать метод принимающий в качестве аргумента коллекцию строк и Predicate работающий со строками.
     * Метод должен выполнить фильтрацию переданной коллекции на основании предиката,
     * затем соединить все отфильтрованные строки между собой при помощи разделителя.
     * Разделителем соединяемых строк выступает символ “|”. В качестве результата выполнения необходимо
     * вернуть строку состоящую из отфильтрованных строк соединенных при помощи разделителя. Например: “hello|,|world|!”
     */
    private static String task3(Collection<String> collection, Predicate<String> predicate) {
        return collection.stream()
                .filter(predicate)
                .collect(Collectors.joining("|"));
    }

    /**
     * Необходимо реализовать метод принимающий в качестве аргумента коллекцию содержащую произвольные
     * неуникальные числа и направление сортировки (ASC, DESC). Метод должен проинициализировать новую
     * коллекцию содержащую только уникальные числа отсортированные в соответствии переданного направления,
     * и вернуть ее в качестве результат выполнения.
     */
    private static Collection<Integer> task4(Collection<Integer> collection, OrderEnum order) {
        return collection.stream()
                .distinct()
                .sorted(order.getComparator())
                .toList();
    }

    /**
     * Необходимо реализовать метод принимающий в качестве аргумента целое число и вычисляющий факториал данного числа.
     * В качестве результата выполнения необходимо вернуть целое число отображающее вычисленный факториал.
     *
     * @param number
     * @return
     */
    private static long task5(int number) {
        return Stream.iterate(1, n -> n + 1)
                .limit(number)
                .reduce(1, (subtotal, element) -> subtotal * element);
    }

    /**
     * Необходимо реализовать программу для группирования по командам бойскаутов.
     * a. Необходимо реализовать класс Boyscout. Данный класс состоит из следующих полей:
     * Имя - строка, Возраст - число, Команда - enum.
     * b. Необходимо реализовать класс Camp. Данный класс хранит коллекцию типа Boyscout.
     * c. В классе Camp необходимо реализовать метод split. Данный метод разделяет (группирует)
     * бойскаутов по командам в соответствии их принадлежности той или иной команде.
     * Далее, каждая команда должна быть отсортирована по возрасту, от старшего к самому младшему.
     * Результатом выполнения метода является структура данных представляющая бойскаутов отсортированных
     * по возрасту и сгруппированных по командам. Например: {“red”, [...], “blue”, [...]}
     */
    private static void task6() {
        Random random = new Random();
        Faker faker = new Faker();

        Camp camp = new Camp();
        TeamEnum[] values = TeamEnum.values();

        Supplier<Boyscout> generator = () -> new Boyscout(
                faker.name().firstName(),
                random.nextInt(10, 30),
                values[random.nextInt(values.length)]
        );

        Stream.generate(generator)
                .limit(10)
                .forEach(camp::add);

        System.out.println(camp.split());
    }
}
