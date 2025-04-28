package org.example.lesson15;

public class Order {
    private int number;
    private String name;

    public Order(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + ":" + name;
    }
}
