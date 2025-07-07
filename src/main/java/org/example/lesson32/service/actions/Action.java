package org.example.lesson32.service.actions;

import org.example.lesson32.jdbc.NetworkDao;
import org.example.lesson32.service.enums.ActionInterface;
import org.example.lesson32.ui.ConsoleController;

import java.sql.SQLException;
import java.util.Optional;

public abstract class Action<T extends Enum<T> & ActionInterface> {
    protected final ConsoleController consoleController;
    protected final NetworkDao networkDao;
    protected final Class<T> enumClass;

    protected Action(ConsoleController consoleController, NetworkDao networkDao, Class<T> enumClass) {
        this.consoleController = consoleController;
        this.networkDao = networkDao;
        this.enumClass = enumClass;
    }

    public void handle() throws SQLException {
        Optional<T> userChoice = consoleController.getUserChoice(enumClass);

        if (userChoice.isPresent()) {
            T action = userChoice.get();

            if (action.getCode() == ActionInterface.BACK_CODE) {
                return;
            }

            try {
                processChoice(action);
            } catch (SQLException e) {
                consoleController.printError(e.getMessage());
            }
        }
    }

    protected abstract void processChoice(T action) throws SQLException;
}
