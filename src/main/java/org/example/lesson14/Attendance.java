package org.example.lesson14;

public class Attendance {
    private String userId;
    private String timestamp;

    public Attendance(String userId, String timestamp) {
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHour() {
        return timestamp.split(":")[0];
    }

    @Override
    public String toString() {
        return userId + ": " + timestamp;
    }
}
