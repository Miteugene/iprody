package org.example.lesson14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttendanceLogger {
    private ArrayList<Attendance> visits;

    public AttendanceLogger() {
        this.visits = new ArrayList<>();
    }

    public void addVisit(Attendance user) {
        visits.add(user);
    }

    public ArrayList<Attendance> getVisits() {
        return visits;
    }

    public Map<String, Integer> getVisitsStat() {
        Map<String, Integer> statMap = new HashMap<>();

        // Ну типа да, вообще можно было вести эту мапу и в методе addVisit и при добавлении сразу заполнять стату
        // Но вопрос: что нам важно: скорость или память
        for (Attendance visit: visits) {
            statMap.put(
                    visit.getUserId(),
                    statMap.getOrDefault(visit.getUserId(), 0) + 1
            );
        }

        return statMap;
    }

    public Map<String, ArrayList<String>> getVisitsGroupedByHour() {
        Map<String, ArrayList<String>> statMap = new HashMap<>();
        String hour;

        // Ну типа тоже при добавлении можно было сразу считать, но вопрос все тот же
        for (Attendance visit: visits) {
            hour = visit.getHour();

            if (!statMap.containsKey(hour)) {
                statMap.put(hour, new ArrayList<>());
            }

            statMap.get(hour).add(visit.getTimestamp());
        }

        return statMap;
    }
}
