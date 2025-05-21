package org.example.lesson22.task2;

public class NotEnoughFuelException extends RuntimeException {
    public NotEnoughFuelException() {
        super("Not enough fuel");
    }
}
