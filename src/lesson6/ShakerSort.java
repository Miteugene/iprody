package lesson6;

public class ShakerSort {
    public static int hande(int[] arr) {
        int leftIdx = 0;
        int rightIdx = arr.length - 1;
        int currentIdx = -1;
        int iterations = 0;

        while (leftIdx < rightIdx) {
            for (int i = leftIdx; i < rightIdx; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    currentIdx = i;
                }
                ++iterations;
            }
            rightIdx = currentIdx;

            if (leftIdx >= rightIdx) {
                break;
            }

            for (int i = rightIdx; i > leftIdx; i--) {
                if (arr[i] < arr[i - 1]) {
                    swap(arr, i, i - 1);
                    currentIdx = i;
                }
                ++iterations;
            }
            leftIdx = currentIdx;
        }

        return iterations;
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
