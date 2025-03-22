package com.example.memory.exception.handler;

import com.example.memory.exception.GenericError;
import com.example.memory.exception.InvalidRequestException;
import com.example.memory.exception.UserCreationFailedException;
import com.example.memory.proto.ApiResponse.ApiResponse;
import com.example.memory.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private GlobalExceptionHandler() {}

    @ExceptionHandler(value = {InvalidRequestException.class})
    protected ResponseEntity<ApiResponse> handleInvalidRequestException(InvalidRequestException ex) {
        log.error(ex.getMessage());
        return ResponseUtils.buildResponse(HttpStatus.OK, false, ex.getStatusCode());
    }

    @ExceptionHandler
    protected ResponseEntity<ApiResponse> handleUserCreationFailedException(UserCreationFailedException ex) {
        log.error(ex.getMessage());
        return ResponseUtils.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR,false,ex.getStatusCode());
    }

    @ExceptionHandler
    protected ResponseEntity<ApiResponse> handleGenericException(GenericError ex) {
        log.error(ex.getMessage());
        return ResponseUtils.buildResponse(ex.getHttpStatus(),false,ex.getStatusCode());
    }
}
