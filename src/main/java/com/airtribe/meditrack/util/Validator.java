package com.airtribe.meditrack.util;

import com.airtribe.meditrack.constants.Constants;
import com.airtribe.meditrack.exception.InvalidDataException;

public final class Validator {

    private Validator() {
    }

    public static String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidDataException("Name cannot be blank");
        }
        return name.trim();
    }

    public static int validateAge(int age) {
        if (age < Constants.MIN_AGE || age > Constants.MAX_AGE) {
            throw new InvalidDataException("Age must be between " + Constants.MIN_AGE + " and " + Constants.MAX_AGE);
        }
        return age;
    }

    public static String validatePhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new InvalidDataException("Phone number cannot be blank");
        }
        return phone.trim();
    }

    public static double validateFee(double fee) {
        if (fee < 0) {
            throw new InvalidDataException("Fee cannot be negative");
        }
        return fee;
    }

    public static String validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new InvalidDataException("Id cannot be blank");
        }
        return id.trim();
    }
}
