package com.example.memory.exception;

import com.example.memory.constants.enums.StatusCodes;

import java.util.List;

public class MissingParameterException extends RuntimeException {
    private final StatusCodes errorStatusCode;

    public MissingParameterException(StatusCodes errorStatusCode) {
        super(errorStatusCode.getMessage());
        this.errorStatusCode = errorStatusCode;
    }

    public MissingParameterException(String... params) {
        this(List.of(params));
    }

    public MissingParameterException(List<String> params) {
        super(String.format("Missing params [%s]", params));
        this.errorStatusCode = StatusCodes.MISSING_PARAMETER_EXCEPTION;
    }

    public StatusCodes getErrorResponseHeader() {
        return this.errorStatusCode;
    }
}
