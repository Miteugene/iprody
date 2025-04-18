package org.example.lesson13.task1;

public class Occurrence {
    private String name;
    private int occurrence;

    public Occurrence(String word, int count) {
        this.name = word;
        this.occurrence = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    @Override
    public String toString() {
        return String.format("{name: \"%s\", occurrence: %d}", name, occurrence);
    }
}
