package org.example.lesson11.task2.obstacles;

import org.example.lesson11.task2.enums.ObstacleType;
import org.example.lesson11.task2.interfaces.ParticipantInterface;

public class Treadmill extends Obstacle {
    private int length;

    public Treadmill(int length) {
        super(ObstacleType.TREADMILL);
        this.length = length;
    }

    @Override
    public boolean overcome(ParticipantInterface participant) {
        return participant.run(length);
    }

    public static Obstacle getRandom() {
        return new Treadmill(random.nextInt(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE));
    }

    protected String getAdditionalInfo() {
        return "Length: " + length;
    }
}
