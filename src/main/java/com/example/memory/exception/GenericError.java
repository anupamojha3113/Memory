package com.example.memory.exception;

import com.example.memory.constants.enums.StatusCodes;
import org.springframework.http.HttpStatus;

public class GenericError extends RuntimeException {
    private final HttpStatus httpStatus;
    private final StatusCodes status;

    public GenericError(StatusCodes status, Throwable cause, HttpStatus httpStatus) {
        super(status.getMessage(), cause);
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public GenericError(HttpStatus httpStatus, StatusCodes status) {
        super(status.getMessage());
        this.httpStatus = httpStatus;
        this.status = status;
    }

    public StatusCodes getStatusCode() {
        return status;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
