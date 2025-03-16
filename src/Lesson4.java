import java.util.Arrays;
import java.util.Random;

public class Lesson4 {

    public static void main(String[] args) {
        Random random = new Random();

        // 1.
        int[] binaryArray = new int[random.nextInt(10, 20)];
        for (int i = 0; i < binaryArray.length; i++) {
            binaryArray[i] = random.nextInt(2);
        }
        System.out.println("Before negative: " + Arrays.toString(binaryArray));
        negative(binaryArray);
        System.out.println("After negative:  " + Arrays.toString(binaryArray));

        // 2.
        hundredInts();

        // 3.
        int[] arr3 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        System.out.println("Before multiplySixByTwo: " + Arrays.toString(arr3));
        multiplySixByTwo(arr3);
        System.out.println("After multiplySixByTwo:  " + Arrays.toString(arr3));

        // 4.
        int[][] arr4 = new int[10][10];
        twoDimensionalArray(arr4);

        // 5.
        System.out.println("Filled array:  " + Arrays.toString(getArray(10, 13)));

        // 6.
        int[] arr6 = new int[random.nextInt(10, 20)];
        for (int i = 0; i < arr6.length; i++) {
            arr6[i] = random.nextInt(100);
        }
        findMinMax(arr6);

        // 7.
        int[] arr7 = new int[10];
        for (int i = 0; i < arr7.length; i++) {
            arr7[i] = random.nextInt(100);
        }
        int[] balancedArr1 = {2, 2, 2, 1, 2, 2, /**/ 10, 1};
        int[] balancedArr2 = {1, 1, 1, /**/ 2, 1};
        System.out.println(isBalanced(arr7));
    }

    /*
    Метод принимает в качестве параметра целочисленный массив, состоящий из элементов 0 и 1.
    При помощи цикла и условия, данный метод заменяет 0 на 1, 1 на 0 соответственно.
    Затем, измененный массив необходимо написать в консоль.
     */
    private static void negative(int[] binaryArray)
    {
        for (int i = 0; i < binaryArray.length; i++) {
            binaryArray[i] = binaryArray[i] == 1 ? 0 : 1;
        }
    }

    /*
    Метод инициализирует внутри себя пустой целочисленный массив длиной 100.
    При помощи цикла необходимо заполнить данный массив значениями от 1 до 100.
    Затем, заполненный значениями массив необходимо написать в консоль.
     */
    private static void hundredInts()
    {
        Random random = new Random();

        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1, 101);
        }

        System.out.println(Arrays.toString(arr));
    }

    /*
    Метод принимает в качестве параметра заранее проинициализированный массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ].
    Необходимо в цикле обойти данный массив и каждый элемент значение которого меньше шести должен быть умножен на два.
    Затем, необходимо вернуть в качестве значения измененный экземпляр массива.
     */
    private static int[] multiplySixByTwo(int[] arr)
    {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }

        return arr;
    }

    /*
    Метод принимает в качестве параметра заранее проинициализированный двумерный (квадратный)
    целочисленный массив с одинаковым количеством строк и столбцов.
    При помощи цикла, необходимо заполнить диагональные (главная диагональ) элементы массива единицами.
    Затем, измененный массив необходимо написать в консоль.
    Повторить задачу с заполнением главной и дополнительной диагоналей.
     */
    private static void twoDimensionalArray(int[][] arr)
    {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j) {
                    arr[i][j] = 1;
                }
            }

            System.out.println(Arrays.toString(arr[i]));
        }

        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (j == arr[i].length - 1 - i) {
                    arr[i][j] = 1;
                }
            }

            System.out.println(Arrays.toString(arr[i]));
        }
    }

    /*
    Метод принимает в качестве параметра два аргумента: len и initialValue.
    Необходимо проинициализировать массив длины len, и заполнить его элементы значениями initialValue.
    Затем, необходимо вернуть в качестве значения измененный экземпляр массива.
     */
    private static int[] getArray(int len, int initialValue)
    {
        int[] arr = new int[len];
        Arrays.fill(arr, initialValue);
        return arr;
    }

    /*
     **Метод принимает в качестве параметра одномерный целочисленный массив предварительно заполненный произвольными значениями.
     * Необходимо найти минимальный и максимальный элементы в данном массиве, и напечатать его в консоль.
     */
    private static void findMinMax(int[] arr)
    {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int number : arr) {
            if (number < min) {
                min = number;
            }
            if (number > max) {
                max = number;
            }
        }

        System.out.println(Arrays.toString(arr));
        System.out.printf("Min: %d, Max: %d", min, max);
    }

    /*
     Метод принимает в качестве параметра одномерный целочисленный массив предварительно заполненный произвольными значениями.
     В данном массиве необходимо найти положение (индекс), в котором сумма левой и правой части значений массива равны.
     Затем, необходимо вернуть true в качестве значения если баланс найден, в против случае - false.
     */
    private static boolean isBalanced(int[] arr)
    {
        System.out.println(Arrays.toString(arr));

        int[] sumItemsFromLeftToRight = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            sumItemsFromLeftToRight[i] = i == 0 ? arr[i] : arr[i] + sumItemsFromLeftToRight[i - 1];
        }

        int sumFromRightToLeft = 0;
        for (int i = arr.length - 1; i > 0; i--) {
            sumFromRightToLeft += arr[i];

            if (sumFromRightToLeft == sumItemsFromLeftToRight[i-1]) {
                return true;
            }
        }

        return false;
    }
}
