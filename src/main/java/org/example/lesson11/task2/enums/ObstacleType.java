package org.example.lesson11.task2.enums;

public enum ObstacleType {
    TREADMILL("Treadmill"),
    WALL("Wall");

    private final String displayName;

    ObstacleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
