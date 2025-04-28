package org.example.lesson15;


import com.github.javafaker.Faker;

public class Lesson15 {
    public static void main(String[] args) {
        Faker faker = new Faker();

        CoffeeOrderBoard orderBoard = new CoffeeOrderBoard();

        Order order;

        for (int i = 0; i < 20; i++) {
            order = orderBoard.add(faker.name().name());

            if (i % 4 == 0) {
                orderBoard.deliver();
            }

            if (i % 5 == 0) {
                orderBoard.deliver(order.getNumber() - 3);
            }

            if (i % 10 == 0) {
                orderBoard.draw();
            }
        }

        orderBoard.draw();
    }
}
