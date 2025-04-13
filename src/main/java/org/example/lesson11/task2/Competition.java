package org.example.lesson11.task2;

import org.example.lesson11.task2.enums.ObstacleType;
import org.example.lesson11.task2.enums.ParticipantType;
import org.example.lesson11.task2.interfaces.ObstacleInterface;
import org.example.lesson11.task2.interfaces.ParticipantInterface;
import org.example.lesson11.task2.obstacles.Obstacle;
import org.example.lesson11.task2.obstacles.Treadmill;
import org.example.lesson11.task2.obstacles.Wall;
import org.example.lesson11.task2.participants.Cat;
import org.example.lesson11.task2.participants.Human;
import org.example.lesson11.task2.participants.Participant;
import org.example.lesson11.task2.participants.Robot;

import java.util.Random;

public class Competition {
    private Random random;
    private Participant[] participants;
    private Obstacle[] obstacles;

    public Competition() {
        this.random = new Random();
    }

    public void start() {
        initParticipants();
        initObstacles();

        boolean result;

        for (Participant participant : this.participants) {
            System.out.println("-----------------------------------------");
            System.out.println("Participant: " + participant.getDisplayName() + " - Start!!!");
            if (participant.isActive()) {
                for (Obstacle obstacle : this.obstacles) {
                    result = obstacle.overcome(participant);
                    System.out.printf(
                            "Participant %s overcomes the obstacle %s, result: %s\n",
                            participant.getDisplayName(),
                            obstacle.getDisplayName(),
                            result ? "SUCCESS" : "FAIL"
                    );
                    if (!result) {
                        break;
                    }
                }
            }
        }
    }

    private void initParticipants() {
        int participantsCount = random.nextInt(5, 10);
        participants = new Participant[participantsCount];

        ParticipantType[] participantTypes = ParticipantType.values();
        ParticipantType currentParticipantType;
        Participant participant;

        for (int i = 0; i < participantsCount; i++) {
            currentParticipantType = participantTypes[random.nextInt(0, participantTypes.length)];

            participant = switch (currentParticipantType) {
                case HUMAN -> Human.getRandom();
                case CAT -> Cat.getRandom();
                case ROBOT -> Robot.getRandom();
            };

            participant.printInfo();

            participants[i] = participant;
        }
    }

    private void initObstacles() {
        int obstaclesCount = random.nextInt(5, 10);
        obstacles = new Obstacle[obstaclesCount];

        ObstacleType[] obstacleTypes = ObstacleType.values();
        ObstacleType currentObstacleType;
        Obstacle obstacle;

        for (int i = 0; i < obstaclesCount; i++) {
            currentObstacleType = obstacleTypes[random.nextInt(0, obstacleTypes.length)];

            obstacle = switch (currentObstacleType) {
                case TREADMILL -> Treadmill.getRandom();
                case WALL -> Wall.getRandom();
            };

            obstacle.printInfo();

            obstacles[i] = obstacle;
        }
    }
}
