package org.example.lesson32.service.actions;

import org.example.lesson32.jdbc.NetworkDao;
import org.example.lesson32.model.Device;
import org.example.lesson32.model.DeviceConnection;
import org.example.lesson32.service.enums.ConnectionActionsEnum;
import org.example.lesson32.ui.ConsoleController;

import java.sql.SQLException;

public class ConnectionAction extends Action<ConnectionActionsEnum> {

    public ConnectionAction(ConsoleController consoleController, NetworkDao networkDao) {
        super(consoleController, networkDao, ConnectionActionsEnum.class);
    }

    @Override
    protected void processChoice(ConnectionActionsEnum action) throws SQLException {
        switch (action) {
            case CREATE_CONNECTION -> create();
            case READ_CONNECTION -> get();
            case DELETE_CONNECTION -> delete();
            default -> throw new RuntimeException("Unexpected behaviour");
        }
    }

    public void create() throws SQLException {
        var devices = networkDao.getAllDevices();
        if (devices.size() < 2) {
            consoleController.printError("Error; add devices first");
            return;
        }

        var connection = consoleController.readNewConnection();
        var deviceFrom = consoleController.selectOf(devices, "device from");

        var remainedDevices = devices.stream()
                .filter(device -> !device.equals(deviceFrom))
                .toList();

        Device deviceTo = consoleController.selectOf(remainedDevices, "device to");

        connection.setDeviceFromId(deviceFrom.getId());
        connection.setDeviceToId(deviceTo.getId());
        DeviceConnection updatedConnection = networkDao.save(connection);
        consoleController.print(updatedConnection);
    }

    public void get() throws SQLException {
        var allConnections = networkDao.getAllConnections();
        consoleController.print(allConnections);
    }

    public void delete() throws SQLException {
        var allConnections = networkDao.getAllConnections();
        if (allConnections.isEmpty()) {
            consoleController.printError("Connections not found");
            return;
        }
        var selectedConnection = consoleController.selectOf(allConnections, "connection");
        networkDao.remove(selectedConnection);
    }
}
