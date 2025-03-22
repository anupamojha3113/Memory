package com.example.memory.dao;


import com.example.memory.proto.entity.UserEntity.UserEntity;
import com.example.memory.proto.enums.Roles;

import java.util.Optional;

public interface UserDAO {

    Optional<UserEntity> getUserDetailsByUserProfileId(String userId);

    Optional<UserEntity> getUserDetailsByEmailOrPhoneNumber(String email, String phoneNumber);

//    DeleteUserResponseDTO deleteUser(String username);

    Optional<UserEntity> createUser(String username, String firstName, String lastName, String email, String phoneNumber, String password,
                                    String profileImageURL, String coverImageURL, String description, String dateOfBirth);
}

