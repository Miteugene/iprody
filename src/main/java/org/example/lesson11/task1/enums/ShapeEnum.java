package org.example.lesson11.task1.enums;

public enum ShapeEnum {
    CIRCLE("Circle"),
    TRIANGLE("Triangle"),
    RECTANGLE("Rectangle");

    private final String displayName;

    ShapeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
