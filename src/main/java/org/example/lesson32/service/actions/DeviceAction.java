package org.example.lesson32.service.actions;

import org.example.lesson32.jdbc.NetworkDao;
import org.example.lesson32.model.Device;
import org.example.lesson32.service.enums.DeviceActionsEnum;
import org.example.lesson32.ui.ConsoleController;

import java.sql.SQLException;
import java.util.List;

public class DeviceAction extends Action<DeviceActionsEnum> {

    public DeviceAction(ConsoleController consoleController, NetworkDao networkDao) {
        super(consoleController, networkDao, DeviceActionsEnum.class);
    }

    @Override
    protected void processChoice(DeviceActionsEnum action) throws SQLException {
        switch (action) {
            case CREATE -> create();
            case READ -> get();
            case UPDATE -> update();
            case DELETE -> delete();
            case GET_WITH_NETWORK -> getWithNetwork();
            default -> throw new RuntimeException("Unexpected behaviour");
        }
    }

    public void create() throws SQLException {
        var networks = networkDao.getAllNetworks();
        if (networks.isEmpty()) {
            consoleController.printError("Error; add networks first");
            return;
        }

        var device = consoleController.readNewDevice();
        var networkToSet = consoleController.selectOf(networks, "network");
        device.setNetworkId(networkToSet.getId());
        Device updatedDevice = networkDao.save(device);
        consoleController.print(updatedDevice);
    }

    public void get() throws SQLException {
        var allDevices = networkDao.getAllDevices();
        consoleController.print(allDevices);
    }

    public void update() throws SQLException {
        var allDevices = networkDao.getAllDevices();
        if (allDevices.isEmpty()) {
            consoleController.printError("Devices not found");
            return;
        }
        var selectedDevice = consoleController.selectOf(allDevices, "device");
        selectedDevice = consoleController.updateFields(selectedDevice);
        selectedDevice = networkDao.update(selectedDevice);
        consoleController.print(selectedDevice);
    }

    public void delete() throws SQLException {
        var allNetworks = networkDao.getAllNetworks();
        if (allNetworks.isEmpty()) {
            consoleController.printError("Networks not found");
            return;
        }
        var selectedNetwork = consoleController.selectOf(allNetworks, "network");
        var devicesOfNetwork = networkDao.getDevicesOf(selectedNetwork);
        if (!devicesOfNetwork.isEmpty()) {
            consoleController.printError("Network has related devices and cannot be removed!");
            return;
        }
        networkDao.remove(selectedNetwork);
    }

    // ---

    public void getWithNetwork() throws SQLException {
        List<Device> devices = networkDao.getAllDevicesWithNetworks();
        for (Device device : devices) {
            System.out.println("Device: " + device.getName());
            System.out.println("  Network: " + device.getNetwork().getName());
        }
    }
}
