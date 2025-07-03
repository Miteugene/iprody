package org.example.lesson32.model;

import org.example.lesson32.validators.IpValidator;
import org.example.lesson32.validators.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Device implements Model{
    private long id;
    private long networkId;
    private String name;
    private String ipAddress;
    private String macAddress;
    private String type;
    private String status;
    private Date createdAt;

    // ---
    private Network network;
    // ---
    private static final Validator IP_VALIDATOR = new IpValidator();

    public Device() {}

    public Device(String name, String ipAddress, String macAddress, String type, String status) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.type = type;
        this.status = status;
    }

    public Device(long id, long networkId, String name, String ipAddress, String macAddress, String type, String status, Date createdAt) {
        this.id = id;
        this.networkId = networkId;
        this.name = name;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static String getTable() {
        return "devices";
    }

    public Network getNetwork() { return network; }
    public void setNetwork(Network network) { this.network = network; }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIpAddress(String ipAddress) {
        IP_VALIDATOR.validate(ipAddress);
        this.ipAddress = ipAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNetworkId(long networkId) {
        this.networkId = networkId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public long getNetworkId() {
        return networkId;
    }

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
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
        return "Device{" +
                "id=" + id +
                ", networkId=" + networkId +
                ", name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return id == device.id && networkId == device.networkId && Objects.equals(name, device.name) && Objects.equals(ipAddress, device.ipAddress) && Objects.equals(macAddress, device.macAddress) && Objects.equals(type, device.type) && Objects.equals(status, device.status) && Objects.equals(createdAt, device.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, networkId, name, ipAddress, macAddress, type, status, createdAt);
    }
}
