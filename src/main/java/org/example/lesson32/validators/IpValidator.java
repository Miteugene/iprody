package org.example.lesson32.validators;

public class IpValidator implements Validator {

    @Override
    public void validate(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("IP address cannot be null or empty");
        }

        String[] parts = value.split("\\.");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid IP address format: " + value);
        }

        for (String part : parts) {
            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    throw new IllegalArgumentException("IP segment out of range: " + part);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Non-numeric IP segment: " + part);
            }
        }
    }
}
