package org.example.lesson32.service.enums;

public enum NetworkActionsEnum implements ActionInterface {
    CREATE(1, "create network"),
    READ(2, "get all networks"),
    UPDATE(3, "edit network"),
    DELETE(4, "delete network"),
    FIND_BY_NAME(5, "find network by name"),
    GET_WITH_DEVICES(6, "get networks with devices"),
    GET_WITH_ACTIVE_DEVICE_COUNT(7, "get networks with active device count"),
    // Other
    BACK(ActionInterface.BACK_CODE, "to main menu");

    private final int code;
    private final String description;

    NetworkActionsEnum(int code, String description) {
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
