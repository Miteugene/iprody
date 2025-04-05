package org.example.lesson5.first;

import java.util.Arrays;

public class MyArrayList {
    private Object[] objects;
    public int length = 0;

    public MyArrayList() {
        this.objects = new Object[this.getNewAllocatedLength()];
    }

    public Object get(int index) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        return objects[index];
    }

    public void put(int index, Object object) {
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        objects[index] = object;
    }

    public void push(Object object) {
        if (length == objects.length) {
            objects = Arrays.copyOf(objects, this.getNewAllocatedLength());
        }

        objects[length] = object;
        length++;
    }

    private int getNewAllocatedLength() {
        return length == 0 ? 5 : (int) (length * 1.5) + 1;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (int i = 0; i < length; i++) {
            Object object = objects[i];
            stringBuilder.append(object == null ? "null" : object.toString());

            if (i < length - 1) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    public int getAllocatedLength() {
        return objects.length;
    }
}
