package org.example.lesson11.task2.participants;

import org.example.lesson11.task2.enums.ParticipantType;

public class Robot extends Participant {

    public Robot(int maxRunLength, int maxJumpHeight) {
        super(ParticipantType.ROBOT, maxRunLength, maxJumpHeight);
    }

    public static Participant getRandom() {
        return new Robot(
                random.nextInt(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE),
                random.nextInt(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE)
        );
    }
}
