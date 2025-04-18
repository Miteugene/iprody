package org.example.lesson12;

import org.example.lesson12.exceptions.ArrayDataException;
import org.example.lesson12.exceptions.ArraySizeException;

public class ArrayValueCalculator {
    private final int ARRAY_SIZE = 4;

    public int doCalc(String[][] arr) throws ArraySizeException, ArrayDataException {
        if (!checkArray(arr)) {
            throw new ArraySizeException(ARRAY_SIZE);
        }

        int sumResult = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sumResult += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new ArrayDataException(
                            String.format("Problem in cell %dx%d received: '%s'", i, j, arr[i][j]),
                            e
                    );
                }
            }
        }

        return sumResult;
    }

    private boolean checkArray(String[][] arr) {
        if (arr.length != ARRAY_SIZE) {
            return false;
        }

        for (String[] row : arr) {
            if (row.length != ARRAY_SIZE) {
                return false;
            }
        }

        return true;
    }
}
