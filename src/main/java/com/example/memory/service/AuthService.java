package com.example.memory.service;

import com.example.memory.constants.enums.StatusCodes;
import com.example.memory.exception.GenericError;
import com.example.memory.exception.UserCreationFailedException;
import com.example.memory.facade.UserFacade;
import com.example.memory.proto.api.CreateUserProfileRequestDTO;
import com.example.memory.proto.api.UserProfileResponseDTO;
import com.example.memory.proto.entity.UserEntity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static com.example.memory.utils.mapper.UserProfileResponseMapper.toUserProfileResponseDTO;

@Service
public class AuthService {
    private static final Logger LOGGER =  LoggerFactory.getLogger(AuthService.class);
    private final UserFacade  userFacade;

    public AuthService(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public UserProfileResponseDTO createUser(CreateUserProfileRequestDTO dto) {
        Optional<UserEntity> createdUserEntity = userFacade.createUser(dto.getUsername(), dto.getFirstName(), dto.getLastName(),dto.getEmail(),dto.getPhoneNumber()
        ,dto.getPassword(),dto.getProfileImage(),dto.getCoverImage(),dto.getDescription(),dto.getDateOfBirth());

        if(createdUserEntity.isEmpty()) {
            throw new UserCreationFailedException(StatusCodes.USER_CREATION_FAILED);
        }

        return toUserProfileResponseDTO(createdUserEntity.get());
    }
}
