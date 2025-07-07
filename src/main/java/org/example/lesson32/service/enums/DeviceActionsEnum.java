package org.example.lesson32.service.enums;

public enum DeviceActionsEnum implements ActionInterface {
    CREATE(1, "create device"),
    READ(2, "get all devices"),
    UPDATE(3, "edit device"),
    DELETE(4, "delete device"),
    GET_WITH_NETWORK(5, "get devices with network"),
    // Other
    BACK(ActionInterface.BACK_CODE, "to main menu");

    private final int code;
    private final String description;

    DeviceActionsEnum(int code, String description) {
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
