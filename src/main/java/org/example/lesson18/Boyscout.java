package org.example.lesson18;

public class Boyscout {
    private String name;
    private int age;
    private TeamEnum team;

    public Boyscout(String name, int age, TeamEnum team) {
        this.name = name;
        this.age = age;
        this.team = team;
    }

    public TeamEnum getTeam() {
        return team;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Boyscout{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", team=" + team +
                '}';
    }
}
