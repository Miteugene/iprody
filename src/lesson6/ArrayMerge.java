package lesson6;

public class ArrayMerge {
    public static int[] hande(int[] arr1, int[] arr2) {
        int[] resultArray = new int[arr1.length + arr2.length];

        int currentIdx = 0;

        for (int item : arr1) {
            resultArray[currentIdx] = item;
            currentIdx++;
        }
        for (int item : arr2) {
            resultArray[currentIdx] = item;
            currentIdx++;
        }

        return resultArray;
    }
}
