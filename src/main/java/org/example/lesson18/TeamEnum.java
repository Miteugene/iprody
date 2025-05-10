package org.example.lesson18;

import java.util.Comparator;

public enum TeamEnum {
    RED("red"), BLUE("blue");

    private String name;

    TeamEnum(String name) { this.name = name; };

    public String getName() { return name; }
}
