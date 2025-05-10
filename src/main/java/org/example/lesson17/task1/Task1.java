package org.example.lesson17.task1;

import java.util.ArrayList;
import java.util.List;

public class Task1<T> {
    public void swapPairs(T[] array) {
        T tempValue;
        int i = 0;
        while (i < array.length - 1) {
            tempValue = array[i];
            array[i] = array[i + 1];
            array[i + 1] = tempValue;
            i += 2;
        }
    }

    public ArrayList<T> arrayToList(T[] array) {
        return new ArrayList<>(List.of(array));
    }
}
