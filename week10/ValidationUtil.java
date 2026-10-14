package com.placement.util;

public final class ValidationUtil {

    private ValidationUtil() {
        // Utility class; prevent object creation.
    }

    public static boolean isNullOrBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidPositiveId(String value) {

        if (isNullOrBlank(value)) {
            return false;
        }

        try {
            int id = Integer.parseInt(value.trim());
            return id > 0;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidEmail(String email) {

        if (isNullOrBlank(email)) {
            return false;
        }

        String value = email.trim();

        return value.matches(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        );
    }
}