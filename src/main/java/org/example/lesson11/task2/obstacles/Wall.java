package org.example.lesson11.task2.obstacles;

import org.example.lesson11.task2.enums.ObstacleType;
import org.example.lesson11.task2.interfaces.ParticipantInterface;

public class Wall extends Obstacle {
    private int height;

    public Wall(int height) {
        super(ObstacleType.WALL);
        this.height = height;
    }

    @Override
    public boolean overcome(ParticipantInterface participant) {
        return participant.jump(height);
    }

    public static Obstacle getRandom() {
        return new Wall(random.nextInt(MIN_RANDOM_VALUE, MAX_RANDOM_VALUE));
    }

    protected String getAdditionalInfo() {
        return "Height: " + height;
    }
}
