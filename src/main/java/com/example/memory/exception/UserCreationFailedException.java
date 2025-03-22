package com.example.memory.exception;

import com.example.memory.constants.enums.StatusCodes;

public class UserCreationFailedException extends RuntimeException {
    public UserCreationFailedException(StatusCodes statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }
    public UserCreationFailedException(StatusCodes statusCode, Throwable cause) {
        super(statusCode.getMessage(), cause);
        this.statusCode = statusCode;
    }
    private final StatusCodes statusCode;

    public StatusCodes getStatusCode() {
        return statusCode;
    }
}
