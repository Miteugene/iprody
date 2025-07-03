package org.example.lesson32;

import org.example.lesson32.validators.IpValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IpValidatorTest {

    private final IpValidator validator = new IpValidator();

    @Test
    void testValidIp() {
        assertDoesNotThrow(() -> validator.validate("192.168.1.1"));
    }

    @Test
    void testInvalidIpFormat() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validate("192.168.1"));
    }

    @Test
    void testInvalidIpSegment() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validate("256.100.100.100"));
    }

    @Test
    void testNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(""));
        assertThrows(IllegalArgumentException.class, () -> validator.validate(null));
    }
}
