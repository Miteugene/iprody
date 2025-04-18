package org.example.lesson11.task2.participants;

import org.example.lesson11.task2.enums.ParticipantType;

public class Cat extends Participant {

    public Cat(int maxRunLength, int maxJumpHeight) {
        super(ParticipantType.CAT, maxRunLength, maxJumpHeight);
    }

    public static Participant getRandom() {
        return new Cat(
                random.nextInt(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE),
                random.nextInt(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE)
        );
    }
}
