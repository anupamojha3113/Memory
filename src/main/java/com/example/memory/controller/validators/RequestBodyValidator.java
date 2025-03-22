package com.example.memory.controller.validators;

import com.example.memory.exception.InvalidRequestException;
import com.example.memory.proto.enums.Roles;

import static com.example.memory.constants.enums.StatusCodes.*;

public class RequestBodyValidator {
    private RequestBodyValidator() {}

    public static void isValidUsername(String username) {
        if (username.isBlank()) {
            throw new InvalidRequestException(ERROR_BLANK_USERNAME);
        }
    }

    public static void isValidPassword(String password) {
        if (password.isBlank()) {
            throw new InvalidRequestException(ERROR_BLANK_PASSWORD);
        }
    }

    public static void isValidPasswordLength(String password) {
        if (password.length() < 8) {
            throw new InvalidRequestException(ERROR_WEAK_PASSWORD);
        }
    }

    public static void isValidRole(String role) {
        if (role.isBlank()) {
            throw new InvalidRequestException(ERROR_BLANK_ROLE);
        } else if (!role.toUpperCase().equals(Roles.ADMIN.toString())
                && !role.toUpperCase().equals(Roles.USER.toString())) {
            throw new InvalidRequestException(ERROR_INVALID_ROLE);
        }
    }

    public static void isValidRequest(String username, String password, String role) {
        isValidUsername(username);
        isValidPassword(password);
        isValidPasswordLength(password);
        isValidRole(role);
    }

    public static void isValidRequest(String username, String password) {
        isValidUsername(username);
        isValidPassword(password);
    }

    public static void isValidRequest(String username) {
        isValidUsername(username);
    }
}
