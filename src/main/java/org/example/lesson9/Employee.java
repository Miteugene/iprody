package org.example.lesson9;

import java.io.PrintStream;

public class Employee {
    private String name;
    private String jobTitle;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Employee(String name, String jobTitle, String email, String phone, int salary, int age) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void printData(PrintStream stream) {
        stream.printf(
                """
                Name: %s
                Job title: %s
                Email: %s
                Phone: %s
                Salary: %d
                Age: %d
                """,
                name,
                jobTitle,
                email,
                phone,
                salary,
                age
        );
    }
}
