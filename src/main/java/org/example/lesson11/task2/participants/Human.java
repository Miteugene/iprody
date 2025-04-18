package org.example.lesson11.task2.participants;

import org.example.lesson11.task2.enums.ParticipantType;

public class Human extends Participant {

    public Human(int maxRunLength, int maxJumpHeight) {
        super(ParticipantType.HUMAN, maxRunLength, maxJumpHeight);
    }

    public static Participant getRandom() {
        return new Human(
                random.nextInt(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE),
                random.nextInt(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE)
        );
    }
}
