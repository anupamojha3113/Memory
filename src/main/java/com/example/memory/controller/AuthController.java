package com.example.memory.controller;

import com.example.memory.constants.URLConstants;
import com.example.memory.constants.enums.StatusCodes;
import com.example.memory.exception.MissingParameterException;
import com.example.memory.proto.ApiResponse.ApiResponse;
import com.example.memory.proto.api.CreateUserProfileRequestDTO;
import com.example.memory.proto.api.UserProfileResponseDTO;
import com.example.memory.service.AuthService;
import com.example.memory.utils.ResponseUtils;
import com.google.protobuf.Any;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(URLConstants.PATH_AUTH)
public class AuthController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<ApiResponse> createUserProfile(@RequestBody CreateUserProfileRequestDTO dto) {
        LOGGER.info("Create user profile dto: {}", dto);

        if (!StringUtils.hasText(dto.getPhoneNumber())) {
            LOGGER.warn("Missing phone_number to create user. Request - {}", dto.getPhoneNumber());
            throw new MissingParameterException(StatusCodes.MISSING_PHONE_NUMBER_EXCEPTION);
        }
        if (!StringUtils.hasText(dto.getEmail())) {
            LOGGER.warn("Missing email to create user. Request - {}", dto.getEmail());
            throw new MissingParameterException(StatusCodes.MISSING_EMAIL_EXCEPTION);
        }
        if (!StringUtils.hasText(dto.getPassword())) {
            LOGGER.warn("Missing password to create user. Request - {}", dto);
            throw new MissingParameterException(StatusCodes.MISSING_PASSWORD_EXCEPTION);
        }
        if (!StringUtils.hasText(dto.getUsername())) {
            LOGGER.warn("Missing legal name to create user. Request - {}", dto.getUsername());
            throw new MissingParameterException(StatusCodes.MISSING_USERNAME_EXCEPTION);
        }
        if (!StringUtils.hasText(dto.getFirstName())) {
            LOGGER.warn("Missing legal name to create user. Request - {}", dto.getFirstName());
            throw new MissingParameterException(StatusCodes.MISSING_FIRSTNAME_EXCEPTION);
        }

        UserProfileResponseDTO responseDTO = authService.createUser(dto);
        return ResponseUtils.buildResponse(HttpStatus.CREATED,true,StatusCodes.SUCCESS_USER_CREATED, Any.pack(responseDTO));
    }

}
