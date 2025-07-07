package org.example.lesson32.service.enums;

public enum ConnectionActionsEnum implements ActionInterface {
    CREATE_CONNECTION(1, "create connection"),
    READ_CONNECTION(2, "get all connections"),
    //UPDATE_CONNECTION(3, "edit connection"),
    DELETE_CONNECTION(4, "delete connection"),
    // Other
    BACK(ActionInterface.BACK_CODE, "to main menu");

    private final int code;
    private final String description;

    ConnectionActionsEnum(int code, String description) {
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
