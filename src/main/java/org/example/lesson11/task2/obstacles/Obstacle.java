package org.example.lesson11.task2.obstacles;

import org.example.lesson11.task2.enums.ObstacleType;
import org.example.lesson11.task2.interfaces.ObstacleInterface;
import org.example.lesson11.task2.interfaces.SomeCompetitionThingInterface;

import java.util.Random;

public abstract class Obstacle implements ObstacleInterface, SomeCompetitionThingInterface {
    protected final static int MIN_RANDOM_VALUE = 5;
    protected final static int MAX_RANDOM_VALUE = 10;

    protected static Random random = new Random();
    private static int count = 0;
    private int id;

    protected ObstacleType obstacleType;

    public Obstacle(ObstacleType obstacleType) {
        count++;
        id = count;
        this.obstacleType = obstacleType;
    }

    @Override
    public String getDisplayName() {
        return this.id + "-" + obstacleType.getDisplayName();
    }

    protected abstract String getAdditionalInfo();

    @Override
    public void printInfo() {
        System.out.printf(
                "Obstacle: %s; %s\n",
                this.getDisplayName(),
                this.getAdditionalInfo()
        );
    }
}
