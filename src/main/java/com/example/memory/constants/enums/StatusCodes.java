package com.example.memory.constants.enums;

public enum StatusCodes {
    SUCCESS("S_000", "Success"),
    ERROR_UNKNOWN("E_000", "Unknown error"),
    ERROR_DATABASE_CONNECTION("E_000", "Database connection error"),

    SUCCESS_USER_CREATED("S_001", "User created successfully"),
    SUCCESS_USER_DELETED("S_002", "User deleted successfully"),
    ERROR_USER_DELETION_FAILED("E_001", "User deleted failed"),
    ERROR_USER_CREATION("E_001", "User creation failed"),
    ERROR_USER_ALREADY_EXISTS("E_002", "Username already exists"),
    ERROR_BLANK_USERNAME("E_003", "Username cannot be blank"),
    ERROR_BLANK_PASSWORD("E_004", "Password cannot be blank"),
    ERROR_BLANK_ROLE("E_005", "Role need to be ADMIN or USER"),
    ERROR_WEAK_PASSWORD("E_006", "Password has to be at least 8 characters"),
    ERROR_INVALID_ROLE("E_007", "Invalid role"),

    ERROR_USER_NOT_FOUND("E_002", "User not found"),

    SUCCESS_LOGIN("S_003", "Login successful"),
    SUCCESS_LOGOUT("S_004", "Logout successful"),
    ERROR_INVALID_CREDENTIALS("E_004", "Invalid username or password"),

    ERROR_UNAUTHORIZED("E_005", "Unauthorized Access - User is not authenticated"),
    ERROR_FORBIDDEN("E_006", "Forbidden Access - User does not have permission to access the resource"),

    ERROR_REFRESH_TOKEN_EXPIRED("E_008", "Refresh token is expired"),
    ERROR_REFRESH_TOKEN_INVALID("E_009", "Refresh token is invalid"),
    MISSING_PARAMETER_EXCEPTION("E_010", "Missing Parameter"),
    MISSING_PHONE_NUMBER_EXCEPTION("E_011", "Missing Phone Number"),
    MISSING_EMAIL_EXCEPTION("E_012", "Missing Email Address"),
    MISSING_USERNAME_EXCEPTION("E_013", "Missing Username"),
    MISSING_PASSWORD_EXCEPTION("E_014", "Missing Password"),
    MISSING_FIRSTNAME_EXCEPTION("E_015", "Missing First Name"),
    USER_CREATION_FAILED("E_016", "User creation failed"),
    MISSING_USERID_EXCEPTION("E_017", "Missing User ID"),
    SUCCESS_REFRESH_TOKEN("S_009", "Refresh token successful"),
    SUCCESS_USER_FETCHED("S_010","User fetched successfully"),
    SUCCESS_FOLLOWER_CREATED("S_011", "Follower created successfully");

    private final String message;
    private final String statusCode;

    StatusCodes(String statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
