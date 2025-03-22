package com.example.memory.controller;

import com.example.memory.constants.enums.StatusCodes;
import com.example.memory.exception.MissingParameterException;
import com.example.memory.proto.ApiResponse.ApiResponse;
import com.example.memory.proto.api.UserProfileResponseDTO;
import com.example.memory.service.UserService;
import com.example.memory.utils.ResponseUtils;
import com.google.protobuf.Any;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.memory.constants.URLConstants.PATH_USER;

@RestController
@RequestMapping(PATH_USER)
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService  userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/profile/{userProfileId}",produces = "application/json")
    public ResponseEntity<ApiResponse> getUserProfile(@PathVariable("userProfileId") String userProfileId) {
        LOGGER.info("Fetching user profile id : {}", userProfileId);
        if (!StringUtils.hasText(userProfileId)) {
            throw new MissingParameterException(StatusCodes.MISSING_USERID_EXCEPTION);
        }
        UserProfileResponseDTO responseDTO = userService.getUserProfile(userProfileId);
        return ResponseUtils.buildResponse(HttpStatus.OK,true,StatusCodes.SUCCESS_USER_FETCHED, Any.pack(responseDTO));
    }
}
