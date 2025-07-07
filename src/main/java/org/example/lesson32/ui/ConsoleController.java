package org.example.lesson32.ui;

import org.example.lesson32.model.Device;
import org.example.lesson32.model.DeviceConnection;
import org.example.lesson32.model.Model;
import org.example.lesson32.model.Network;
import org.example.lesson32.service.enums.ActionInterface;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleController {

    private final Scanner scanner;

    public ConsoleController(Scanner scanner) {
        this.scanner = scanner;
    }

    public <T extends Enum<T> & ActionInterface> Optional<T> getUserChoice(Class<T> enumClass) {
        System.out.println("Enter: ");
        for (var action : enumClass.getEnumConstants()) {
            System.out.println(action.getCode() + " " + action.getDescription());
        }

        int code = scanner.nextInt();
        scanner.nextLine();

        return ActionInterface.valueOf(code, enumClass);
    }

    public <T extends Model> T selectOf(List<T> models, String type) {
        System.out.println("Select " + type + " by id:");

        while (true) {
            models.forEach(System.out::println);

            int modelId = scanner.nextInt();
            scanner.nextLine();

            T selectedModel = null;
            for (var model : models) {
                if (model.getId() == modelId) {
                    selectedModel = model;
                    break;
                }
            }

            if (selectedModel == null)
                System.out.println("id was incorrect; try again");
            else
                return selectedModel;
        }
    }

    public <T extends Model> void print(List<T> models) {
        models.forEach(System.out::println);
    }

    public Device readNewDevice() {
        return updateFields(new Device());
    }

    public DeviceConnection readNewConnection() {
        return updateFields(new DeviceConnection());
    }

    public Network readNewNetwork() {
        return updateFields(new Network());
    }

    public Device updateFields(Device device) {
        String name = enterStringField("device", "name");
        String ipAddress = enterStringField("device", "ip");
        String macAddress = enterStringField("device", "mac");
        String status = enterStringField("device", "status");
        String type = enterStringField("device", "type");

        device.setName(name);
        device.setIpAddress(ipAddress);
        device.setMacAddress(macAddress);
        device.setStatus(status);
        device.setType(type);

        return device;
    }

    public DeviceConnection updateFields(DeviceConnection deviceConnection) {
        //long deviceFromId = enterLongField("connection", "deviceFromId");
        //long deviceToId = enterLongField("connection", "deviceToId");
        String type = enterStringField("connection", "type");
        String status = enterStringField("connection", "status");

        //deviceConnection.setDeviceFromId(deviceFromId);
        //deviceConnection.setDeviceToId(deviceToId);
        deviceConnection.setType(type);
        deviceConnection.setStatus(status);

        return deviceConnection;
    }

    public Network updateFields(Network network) {
        String name = enterStringField("network", "name");
        String description = enterStringField("network", "description");

        network.setName(name);
        network.setDescription(description);
        return network;
    }

    public String readNetworkName() {
        return enterStringField("network", "name");
    }

    public void print(Network network) {
        System.out.println(network.toString());
    }

    public void print(Device device) {
        System.out.println(device.toString());
    }

    public void print(DeviceConnection deviceConnection) {
        System.out.println(deviceConnection.toString());
    }

    public void printError(String message) {
        System.out.println(message);
    }


    public String enterStringField(String entityName, String fieldName) {
        return enterString("Enter " + entityName + " " + fieldName + ":");
    }

    public long enterLongField(String entityName, String fieldName) {
        return enterLong("Enter " + entityName + " " + fieldName + ":");
    }

    public String enterString(String request) {
        System.out.println(request);
        return scanner.nextLine();
    }

    public long enterLong(String request) {
        System.out.println(request);
        return scanner.nextLong();
    }
}


