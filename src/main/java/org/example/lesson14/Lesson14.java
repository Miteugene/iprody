package org.example.lesson14;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Lesson14 {
    public static void main(String[] args) {
        Random random = new Random();
        Faker faker = new Faker();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        List<String> users = List.of("user1", "user2", "user3", "user4", "user5");

        AttendanceLogger attendanceLogger = new AttendanceLogger();

        String userId;
        String timestamp;

        for (int i = 0; i < 20; i++) {
            userId = users.get(random.nextInt(0, users.size()));
            timestamp = sdf.format(faker.date().birthday());

            attendanceLogger.addVisit(
                    new Attendance(userId, timestamp)
            );
        }

        System.out.println(attendanceLogger.getVisits());
        System.out.println("=====================================");
        System.out.println(attendanceLogger.getVisitsStat());
        System.out.println("=====================================");
        System.out.println(attendanceLogger.getVisitsGroupedByHour());
    }
}
