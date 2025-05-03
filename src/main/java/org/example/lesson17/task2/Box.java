package org.example.lesson17.task2;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private final ArrayList<T> container = new ArrayList<>();

    public void add(T fruit) {
        container.add(fruit);
    }

    public float getWeight() {
        float sumWeight = 0;
        for (T fruit : container) {
            sumWeight += fruit.getWeight();
        }
        return sumWeight;
    }

    public int getFruitsCount() {
        return container.size();
    }

    public float compare(Box<?> anotherBox) {
        return this.getWeight() - anotherBox.getWeight();
    }

    public void transfer(Box<T> anotherBox) {
        while (!anotherBox.container.isEmpty()) {
            container.add(anotherBox.container.removeLast());
        }
    }
}
