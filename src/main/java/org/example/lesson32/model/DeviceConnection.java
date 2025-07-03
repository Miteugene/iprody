package org.example.lesson32.model;

import java.util.Date;

public class DeviceConnection implements Model{
    private long id;
    private long deviceFromId;
    private long deviceToId;
    private String type;
    private String status;
    private Date createdAt;

    public DeviceConnection() {}

    public DeviceConnection(String type, String status) {
        this.type = type;
        this.status = status;
    }

    public DeviceConnection(long id, long deviceFromId, long deviceToId, String type, String status, Date createdAt) {
        this.id = id;
        this.deviceFromId = deviceFromId;
        this.deviceToId = deviceToId;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static String getTable() {
        return "connections";
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDeviceFromId(long deviceFromId) {
        this.deviceFromId = deviceFromId;
    }

    public void setDeviceToId(long deviceToId) {
        this.deviceToId = deviceToId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public long getDeviceFromId() {
        return deviceFromId;
    }

    public long getDeviceToId() {
        return deviceToId;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "DeviceConnection{" +
                "id=" + id +
                ", deviceFromId=" + deviceFromId +
                ", deviceToId=" + deviceToId +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
