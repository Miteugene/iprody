package org.example.lesson11.task2.participants;

import org.example.lesson11.task2.enums.ParticipantType;
import org.example.lesson11.task2.interfaces.ParticipantInterface;
import org.example.lesson11.task2.interfaces.SomeCompetitionThingInterface;

import java.util.Random;

public abstract class Participant implements ParticipantInterface, SomeCompetitionThingInterface {
    protected final static int MIN_RANDOM_VALUE = 15;
    protected final static int MAX_RANDOM_VALUE = 20;

    protected static Random random = new Random();
    protected ParticipantType participantType;

    private static int count = 0;
    private int id;

    protected int maxRunLength;
    protected int maxJumpHeight;
    private boolean isActive = true;

    public Participant(ParticipantType participantType, int maxRunLength, int maxJumpHeight) {
        count++;
        id = count;
        this.participantType = participantType;
        this.maxRunLength = maxRunLength;
        this.maxJumpHeight = maxJumpHeight;
    }

    public void disable() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public boolean run(int length) {
        if (length > maxRunLength) {
            disable();
            return false;
        }

        maxRunLength -= length;
        return true;
    }

    @Override
    public boolean jump(int height) {
        if (height > maxJumpHeight) {
            disable();
            return false;
        }

        maxJumpHeight -= height;
        return true;
    }

    @Override
    public String getDisplayName() {
        return this.id + "-" + participantType.getDisplayName();
    }

    @Override
    public void printInfo() {
        System.out.printf(
                "Participant: %s; Max run length: %d; Max jump height: %d\n",
                this.getDisplayName(),
                this.maxRunLength,
                this.maxJumpHeight
        );
    }
}
