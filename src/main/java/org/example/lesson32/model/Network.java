package org.example.lesson32.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Network implements Model {
    private long id;
    private String name;
    private String description;
    private Date createdAt;
    // ---
    private List<Device> devices = new ArrayList<>();
    private int activeDeviceCount;

    public Network() {}

    public Network(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Network(long id, String name, String description, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static String getTable() {
        return "networks";
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public int getActiveDeviceCount() {
        return activeDeviceCount;
    }

    public void setActiveDeviceCount(int activeDeviceCount) {
        this.activeDeviceCount = activeDeviceCount;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Network{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
