import java.util.Random;

public class HomeWorkApp {
    public static void main(String[] args) {
        Random random = new Random();

        int a = random.nextInt(101) - 50;
        int b = random.nextInt(101) - 50;
        int count = random.nextInt(4) + 1;
        int year = random.nextInt(1000) + 2000;

        printThreeWords();

        checkSumSign();

        printColor();

        compareNumbers();

        System.out.printf("a = %d, b = %d, result of isSumInRangeFrom10To20 = %b\n", a, b, isSumInRangeFrom10To20(a, b));

        System.out.printf("a = %d; ", a);
        checkPositive(a);

        System.out.printf("a = %d, result of isNegative = %b\n", a, isNegative(a));

        System.out.printf("count = %d\n", count);
        printStringInLoop("String example", count);

        System.out.printf("year: %d - is leap: %b", year, isLeapYear(year));
    }

    /*
    Создайте метод printThreeWords(), который при вызове должен отпечатать в столбец три слова:
    Orange, Banana, Apple.
     */
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    /*
    Создайте метод checkSumSign(), в теле которого объявите две int переменные a и b,
    и инициализируйте их любыми значениями, которыми захотите.
    Далее метод должен просуммировать эти переменные, и если их сумма больше или равна 0,
    то вывести в консоль сообщение “Сумма положительная”, в противном случае - “Сумма отрицательная”;
     */
    public static void checkSumSign() {
        Random random = new Random();

        int a = random.nextInt(101) - 50;
        int b = random.nextInt(101) - 50;

        int sum = a + b;
        System.out.printf("a = %d, b = %d, sum = %d; ", a, b, sum);

        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    /*
    Создайте метод printColor() в теле которого задайте int переменную value и инициализируйте ее любым значением.
    Если value меньше 0 (0 включительно), то в консоль метод должен вывести сообщение “Красный”,
    если лежит в пределах от 0 (0 исключительно) до 100 (100 включительно),
    то “Желтый”, если больше 100 (100 исключительно) - “Зеленый”;
     */
    public static void printColor() {
        Random random = new Random();

        int value = random.nextInt(1001) - 500;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    /*
    Создайте метод compareNumbers(), в теле которого объявите две int переменные a и b,
    и инициализируйте их любыми значениями, которыми захотите. Если a больше или равно b,
    то необходимо вывести в консоль сообщение “a >= b”, в противном случае “a < b”;
     */
    public static void compareNumbers() {
        Random random = new Random();

        int a = random.nextInt(101) - 50;
        int b = random.nextInt(101) - 50;
        boolean result = a >= b;
        System.out.printf("a = %d, b = %d, a >= b = %b; ", a, b, result);

        if (result) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    /*
    Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит в пределах от 10 до 20 (включительно),
    если да – вернуть true, в противном случае – false.
     */
    public static boolean isSumInRangeFrom10To20(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    /*
    Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
    положительное ли число передали или отрицательное.
    Замечание: ноль считаем положительным числом.
     */
    public static void checkPositive(int number) {
        if (number >= 0) {
            System.out.println("positive");
        } else {
            System.out.println("negative");
        }
    }

    /*
    Написать метод, которому в качестве параметра передается целое число.
    Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
     */
    public static boolean isNegative(int number) {
        return number < 0;
    }

    /*
    Написать метод, которому в качестве аргументов передается строка и число,
    метод должен отпечатать в консоль указанную строку, указанное количество раз;
     */
    public static void printStringInLoop(String str, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(str);
        }
    }

    /*
    "*"Написать метод, который определяет, является ли год високосным,
    и возвращает boolean (високосный - true, не високосный - false).
    Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
