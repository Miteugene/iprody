package org.example.lesson11.task2.enums;

public enum ParticipantType {
    HUMAN("Human"),
    CAT("Cat"),
    ROBOT("Robot");

    private final String displayName;

    ParticipantType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
