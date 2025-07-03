package org.example.lesson32.service.actions;

import org.example.lesson32.jdbc.NetworkDao;
import org.example.lesson32.model.Device;
import org.example.lesson32.model.Network;
import org.example.lesson32.service.enums.NetworkActionsEnum;
import org.example.lesson32.ui.ConsoleController;

import java.sql.SQLException;
import java.util.List;

public class NetworkAction extends Action<NetworkActionsEnum> {

    public NetworkAction(ConsoleController consoleController, NetworkDao networkDao) {
        super(consoleController, networkDao, NetworkActionsEnum.class);
    }

    @Override
    protected void processChoice(NetworkActionsEnum action) throws SQLException {
        switch (action) {
            case CREATE -> create();
            case READ -> get();
            case UPDATE -> update();
            case DELETE -> delete();
            case FIND_BY_NAME -> findByName();
            case GET_WITH_DEVICES -> getWithDevices();
            case GET_WITH_ACTIVE_DEVICE_COUNT -> getNetworksWithActiveDeviceCount();
            default -> throw new RuntimeException("Unexpected behaviour");
        }
    }

    public void create() throws SQLException {
        var network = consoleController.readNewNetwork();
        network = networkDao.save(network);
        consoleController.print(network);
    }

    public void get() throws SQLException {
        var allNetworks = networkDao.getAllNetworks();
        consoleController.print(allNetworks);
    }

    public void update() throws SQLException {
        var allNetworks = networkDao.getAllNetworks();
        if (allNetworks.isEmpty()) {
            consoleController.printError("Networks not found");
            return;
        }
        var selectedNetwork = consoleController.selectOf(allNetworks, "network");
        selectedNetwork = consoleController.updateFields(selectedNetwork);
        selectedNetwork = networkDao.update(selectedNetwork);
        consoleController.print(selectedNetwork);
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

    // Поиск по названию Сети
    public void findByName() throws SQLException {
        String networkName = consoleController.readNetworkName();
        var allNetworks = networkDao.getAllNetworksLike(networkName);
        consoleController.print(allNetworks);
    }

    public void getWithDevices() throws SQLException {
        List<Network> networks = networkDao.getAllNetworksWithDevices();

        for (Network network : networks) {
            System.out.println("Network: " + network.getName());
            for (Device device : network.getDevices()) {
                System.out.println("  - Device: " + device.getName());
            }
        }
    }

    public void getNetworksWithActiveDeviceCount() throws SQLException {
        List<Network> networks = networkDao.getNetworksWithDeviceCount("active");

        for (Network network : networks) {
            System.out.println(network.getName() + ": " + network.getActiveDeviceCount() + " active devices");
        }
    }
}

