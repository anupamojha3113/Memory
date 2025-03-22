package com.example.memory.exception;


import com.example.memory.constants.enums.StatusCodes;

public class InvalidRequestException extends RuntimeException {
    private final StatusCodes status;

    public InvalidRequestException(StatusCodes status) {
        super(status.getMessage());
        this.status = status;
    }

    public InvalidRequestException(StatusCodes status,Throwable cause) {
        super(status.getMessage(), cause);
        this.status = status;
    }

    public StatusCodes getStatusCode() {
        return status;
    }
}
