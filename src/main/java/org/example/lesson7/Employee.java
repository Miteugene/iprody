package org.example.lesson7;

/**
 * Создать класс "Employee" с полями: ФИО, должность, email, телефон, возраст.
 * Конструктор данного класса должен заполнять эти поля при создании объекта.
 */
public class Employee {
    private String name;
    private String jobTitle;
    private String email;
    private String phone;
    private int age;

    public Employee(String name, String jobTitle, String email, String phone, int age) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.email = email;
        this.phone = phone;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
