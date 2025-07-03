package org.example.lesson32.service.enums;

import java.util.Arrays;
import java.util.Optional;

public interface ActionInterface {
    int BACK_CODE = 99;
    int getCode();
    String getDescription();

    static <T extends Enum<T> & ActionInterface> Optional<T> valueOf(int code, Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(action -> action.getCode() == code)
                .findAny();
    }
}
