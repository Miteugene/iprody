package org.example.lesson9;

import java.io.PrintStream;

import com.github.javafaker.Faker;

public class Lesson9 {
    public static void main(String[] args) {
        PrintStream stream = System.out;
        Faker faker = new Faker();

        int arrLen = 5;

        Employee[] employees = new Employee[arrLen];

        for (int i = 0; i < employees.length; i++) {
            employees[i] = new Employee(
                    faker.name().fullName(),
                    faker.job().title(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().phoneNumber(),
                    faker.number().numberBetween(2000, 4000),
                    faker.number().numberBetween(20, 60)
            );
        }

        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() > 40) {
                stream.println("=============================");
                employees[i].printData(stream);
            }
        }
    }
}
