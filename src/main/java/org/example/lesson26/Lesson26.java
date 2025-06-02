package org.example.lesson26;

import java.util.*;
import java.util.stream.Collectors;

public class Lesson26 {
    public int[] getSubArray(int[] arr, int target) {
        int lastOccurrence = findLastOccurrence(arr, target);

        if (lastOccurrence == -1 || lastOccurrence == arr.length - 1) {
            throw new RuntimeException("There is no " + target + " in the array");
        }

        return Arrays.copyOfRange(arr, lastOccurrence + 1, arr.length);
    }

    public boolean isOnlyFromTargetNumbers(int[] arr, int[] targetNumbers) {
        if (arr == null || targetNumbers == null) {
            throw new RuntimeException("One of the arrays is null");
        }

        HashSet<Integer> hashSet = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));

        HashSet<Integer> targetHashSet = Arrays.stream(targetNumbers)
                .boxed()
                .collect(Collectors.toCollection(HashSet::new));

        return hashSet.equals(targetHashSet);
    }

    private int findLastOccurrence(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
