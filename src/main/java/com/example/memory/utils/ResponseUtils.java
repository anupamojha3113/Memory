package com.example.memory.utils;

import com.example.memory.constants.enums.StatusCodes;
import com.example.memory.proto.ApiResponse.ApiResponse;
import com.google.protobuf.Any;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    ResponseUtils() {}
    public static ResponseEntity<ApiResponse> buildResponse(HttpStatus status, boolean isSuccess, StatusCodes statusCodes) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.newBuilder()
                        .setSuccess(isSuccess)
                        .setStatusCode(statusCodes.getStatusCode())
                        .setMessage(statusCodes.getMessage())
                        .build());
    }

    public static ResponseEntity<ApiResponse> buildResponse(HttpStatus status, boolean isSuccess, StatusCodes statusCodes,Any data) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ApiResponse.newBuilder()
                        .setSuccess(isSuccess)
                        .setStatusCode(statusCodes.getStatusCode())
                        .setMessage(statusCodes.getMessage())
                        .setData(data)
                        .build());
    }
}
