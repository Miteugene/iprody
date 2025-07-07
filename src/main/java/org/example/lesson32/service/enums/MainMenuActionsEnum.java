package org.example.lesson32.service.enums;

import java.util.Arrays;
import java.util.Optional;

public enum MainMenuActionsEnum implements ActionInterface {
    NETWORK(1, "to network menu"),
    DEVICE(2, "to device menu"),
    CONNECTION(3, "to connection menu"),
    // Other
    EXIT(ActionInterface.BACK_CODE, "exit");

    private final int code;
    private final String description;

    MainMenuActionsEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
