package com.example.memory.service;

import com.example.memory.constants.enums.StatusCodes;
import com.example.memory.exception.GenericError;
import com.example.memory.facade.UserFacade;
import com.example.memory.proto.api.UserProfileResponseDTO;
import com.example.memory.proto.entity.UserEntity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.memory.utils.mapper.UserProfileResponseMapper.toUserProfileResponseDTO;

@Service
public class UserService {
    private final UserFacade userFacade;

    public UserService(UserFacade userFacade) {
        this.userFacade = userFacade;
    }
    public UserProfileResponseDTO getUserProfile(String userProfileId) {
        Optional<UserEntity> userEntityResponse = userFacade.getUserDetailsByUserProfileId(userProfileId);
        if(userEntityResponse.isEmpty()) {
            throw new GenericError(HttpStatus.NOT_FOUND, StatusCodes.ERROR_USER_NOT_FOUND);
        }
        return toUserProfileResponseDTO(userEntityResponse.get());
    }
}
