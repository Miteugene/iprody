package org.example.lesson32.service;

import org.example.lesson32.jdbc.NetworkDao;
import org.example.lesson32.service.actions.ConnectionAction;
import org.example.lesson32.service.actions.DeviceAction;
import org.example.lesson32.service.actions.NetworkAction;
import org.example.lesson32.service.enums.MainMenuActionsEnum;
import org.example.lesson32.ui.ConsoleController;

import java.sql.SQLException;
import java.util.Optional;

public class NetworksService {
    private final ConsoleController consoleController;
    private final NetworkAction networkAction;
    private final DeviceAction deviceAction;
    private final ConnectionAction connectionAction;

    public NetworksService(ConsoleController consoleController, NetworkDao networkDao) {
        this.consoleController = consoleController;

        networkAction = new NetworkAction(consoleController, networkDao);
        deviceAction = new DeviceAction(consoleController, networkDao);
        connectionAction = new ConnectionAction(consoleController, networkDao);
    }

    public void process() {
        while (true) {
            Optional<MainMenuActionsEnum> userAction = consoleController.getUserChoice(MainMenuActionsEnum.class);
            if (userAction.isPresent()) {
                var action = userAction.get();

                if (action == MainMenuActionsEnum.EXIT) {
                    break;
                }

                try {
                    processUserChoice(action);
                } catch (SQLException e) {
                    consoleController.printError(e.getMessage());
                }
            } else {
                consoleController.printError("Code was incorrect; try again");
            }
        }
    }

    public void processUserChoice(MainMenuActionsEnum action) throws SQLException {
        switch (action) {
            case NETWORK -> networkAction.handle();
            case DEVICE -> deviceAction.handle();
            case CONNECTION -> connectionAction.handle();
            default -> throw new RuntimeException("Unexpected behaviour");
        }
    }
}
